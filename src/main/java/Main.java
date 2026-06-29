import todoapp.repository.TaskRepository;
import todoapp.ui.ConsoleMenu;

import java.sql.*;


public class Main {
    public static void main(String [] args) throws Exception {
        Connection connection = DriverManager.getConnection(  //подключение
                "jdbc:postgresql://localhost:5432/todo_db",
                "postgres",
                "2391"
        );
        TaskRepository repository = new TaskRepository(connection);
        ConsoleMenu consoleMenu = new ConsoleMenu(repository);
        consoleMenu.start();
        connection.close();
    }
}

