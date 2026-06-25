import java.sql.*;
import java.util.Scanner;

public class Main {
    static void main() throws SQLException {
        Connection connection = DriverManager.getConnection(  //подключение
                "jdbc:postgresql://localhost:5432/todo_db",
                "postgres",
                "2391"
        );
        System.out.println("Подключился к БД");

//        PreparedStatement stmt = connection.prepareStatement(
//                "INSERT INTO  tasks (title) VALUES (?)" //Готовим запрос — вместо реального значения ставим "?"
//        );                                              //для защиты от sql-инъекций
//
//        stmt.setString(1,"Выучить jdbc");
//        stmt.executeUpdate();
//        System.out.println("Задача добавлена");

        ResultSet rs = connection.createStatement().executeQuery(           //читаем бд
          "SELECT * FROM tasks"
        );
        while (rs.next()){
            System.out.println(rs.getInt("id")+"|"+
                    rs.getString("title")+"|"+
                    rs.getBoolean("done"));
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Показать задачи");
        System.out.println("2. Добавить задачу");
        System.out.println("0. Выход");
        System.out.println("Выбор: ");





        connection.close();






    }
}
