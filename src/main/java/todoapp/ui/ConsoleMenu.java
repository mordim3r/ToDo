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
            System.out.println("5. Показать невыполненные задачи");
            System.out.println("0. Выход");
            System.out.println("Выбор: ");
            String choise = sc.nextLine();
            switch (choise) {
                case "1" -> {
                    List<Task> tasks = repository.findAll();
                    printTasks(tasks);
                }
                case "2" -> {
                    System.out.println("Название задачи");
                    String title = sc.nextLine().trim();
                    if (title.isEmpty()) {
                        System.out.println("Название задачи не может быть пустым!");
                        break;
                    }
                    // передаем только title, потому что индекс база проставит сама через SERIAL,
                    // а введенный проигнорирует и boolean в базе по умолчанию false
                    Task task = new Task(title);
                    repository.addTask(task);
                }
                case "3" -> {
                    List<Task> tasks = repository.findAll();
                    if (tasks.isEmpty()) {
                        System.out.println("Задач нет");
                        break;
                    } else {
                        printTasks(tasks);
                        System.out.println("Какую задачу выбрать? Введите номер: ");
                        try {
                            int number = Integer.parseInt(sc.nextLine());
                            Task selected = tasks.get(number - 1);   //список с нуля начинается, а не с 1
                            repository.markDone(selected);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Задачи с таким номером нет. Введите правильный номер");
                        } catch (NumberFormatException e) {
                            System.out.println("Введите число!");
                        }
                    }
                }
                case "4" -> {
                    List<Task> tasks = repository.findAll();
                    if (tasks.isEmpty()) {
                        System.out.println("Задач нет");
                        break;
                    } else {
                        printTasks(tasks);
                        System.out.println("Какую задачу удалить? Введите номер:");
                        try {
                            int number = Integer.parseInt(sc.nextLine());
                            Task selected = tasks.get(number - 1);//список с нуля начинается, а не с 1
                            repository.deleteTask(selected);
                        } catch (IndexOutOfBoundsException e) {
                            System.out.println("Задачи с таким номером нет. Введите правильный номер");
                        } catch (NumberFormatException e) {
                            System.out.println("Введите число!");
                        }
                    }
                }
                case "5" -> {
                    List<Task> tasks = repository.unexecutedTasks();
                    printTasks(tasks);
                }
                case "0" -> running = false;
                default -> System.out.println("Неизвестная команда");
            }
        }
    }



    private void printTasks(List<Task> tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Задач нет");
            return;
        } else {
            int number = 1;
            for (Task i : tasks) {
                System.out.println(number + " " + i);
                number++;
            }
        }
    }
}
