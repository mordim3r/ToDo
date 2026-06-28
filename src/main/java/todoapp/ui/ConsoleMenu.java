package todoapp.ui;

import todoapp.model.Task;
import todoapp.repository.TaskRepository;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private Scanner sc;

    private TaskRepository repository;



    //не принимаем сканер, а создаем новый(так проще)
    public ConsoleMenu(TaskRepository repository) {
        this.sc = new Scanner(System.in);
        this.repository = repository;
    }



    public void start() throws Exception {
        boolean running = true;
        while (running) {
            System.out.println("\n1. Показать задачи");
            System.out.println("2. Добавить задачу");
            System.out.println("3. Отметить задачу выполненной");
            System.out.println("4. Удалить задачу");
            System.out.println("0. Выход");
            System.out.println("Выбор: ");
            String choise = sc.nextLine();
            switch (choise) {
                case "1" -> {
                    List<Task> tasks = repository.findAll();
                    for (Task i : tasks) {
                        System.out.println(i);
                    }
                }
                case "2" -> {
                    System.out.println("Название задачи");
                    String title = sc.nextLine();
                    // передаем только title, потому что индекс база проставит сама через SERIAL,
                    // а введенный проигнорирует и boolean в базе по умолчанию false
                    Task task = new Task(title);
                    repository.addTask(task);
                }
                case "3" -> {
                    List<Task> tasks = repository.findAll();
                    //показываем список всегда через цикл
                    for (Task i : tasks) {
                        System.out.println(i);
                    }
                    System.out.println("Какую задачу выбрать? Введи номер: ");
                    int id = Integer.parseInt(sc.nextLine());
                    //markDone принимает объект Task task, поэтому и передавать нужно не просто число(id),
                    //а объект, который соответствует этому id
                    for (Task i : tasks) {
                        if (i.getId() == id) {
                            repository.markDone(i);
                        }
                    }
                }
                case "4" -> {
                    List<Task> tasks = repository.findAll();
                    //показываем список всегда через цикл
                    for (Task i : tasks) {
                        System.out.println(i);
                    }
                    System.out.println("Какую задачу удалить? Введи номер:");
                    int id = Integer.parseInt(sc.nextLine());
                    //deleteTask принимает объект Task task, поэтому и передавать нужно не просто число(id),
                    //а объект, который соответствует этому id
                    for (Task i : tasks) {
                        if (i.getId() == id) {
                            repository.deleteTask(i);
                        }
                    }
                }
                case "0" -> running = false;
                default -> System.out.println("Неизвестная команда");
            }
        }
    }
}
