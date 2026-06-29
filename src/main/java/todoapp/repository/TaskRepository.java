package todoapp.repository;

import todoapp.model.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


//соединение с бд
public class TaskRepository {
    private Connection connection;



    public TaskRepository(Connection connection) {
        this.connection = connection;
    }



    public List<Task> findAll() throws Exception {
        List<Task> tasks = new ArrayList<>();
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(           //читаем бд
                        "SELECT * FROM tasks"
                )
        ) {
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("title"), rs.getBoolean("done"), rs.getDate("created_date").toLocalDate());
                tasks.add(task);
            }
        }
        return tasks;
    }



    public void addTask(Task task) throws SQLException {
        try (
                //Готовим запрос — вместо реального значения ставим "?"
                //для защиты от sql-инъекций
                PreparedStatement stmt = connection.prepareStatement(
                        "INSERT INTO  tasks (title) VALUES (?)")
        ) {
            stmt.setString(1, task.getTitle());
            stmt.executeUpdate();
        }
        System.out.println("Задача добавлена");
    }



    public void markDone(Task task) throws Exception {
        try (//Готовим запрос — вместо реального значения ставим "?"
             //для защиты от sql-инъекций
             PreparedStatement stmt = connection.prepareStatement(
                     "UPDATE tasks SET done = TRUE WHERE id= ?"
             )) {
            stmt.setInt(1, task.getId());
            stmt.executeUpdate();
        }
        System.out.println("Задача отмечена выполненной");
    }



    public void deleteTask(Task task) throws Exception {
        try (//Готовим запрос — вместо реального значения ставим "?"
             //для защиты от sql-инъекций
             PreparedStatement stmt = connection.prepareStatement(
                     "DELETE FROM tasks WHERE id= ?"
             )) {
            stmt.setInt(1, task.getId());
            stmt.executeUpdate();
        }
        System.out.println("Задача удалена");
    }

    public List<Task> unexecutedTasks()throws Exception{
        List<Task> tasks = new ArrayList<>();
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(           //читаем бд
                        "SELECT * FROM tasks WHERE done = false"
                )
        ) {
            while (rs.next()) {
                Task task = new Task(rs.getInt("id"), rs.getString("title"), rs.getBoolean("done"), rs.getDate("created_date").toLocalDate());
                tasks.add(task);
            }
        }
        return tasks;
    }
}



