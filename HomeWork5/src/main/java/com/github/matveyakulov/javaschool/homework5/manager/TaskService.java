package com.github.matveyakulov.javaschool.homework5.manager;

import com.github.matveyakulov.javaschool.homework5.model.Task;

import java.io.IOException;
import java.util.Scanner;

/**
 * UI.
 */
public class TaskService {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        String fileName = "HomeWork5/src/main/resources/tasks/tasks.txt";  // путь к файлу о умолчанию
        TaskCache taskCache = new TaskCache(fileName);   // создание множества из указанного пути

        try {
            taskCache.readObjects();   // заполнение множества задачами из файла
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Данные из файла не были считаны(");
        }
        System.out.println("Сейчас во множестве " + taskCache.size() + " задач.");
        while (true) {
            System.out.println("Что вы хотите сделать?" +
                    " 0 - добавить новую задачу," +
                    " 1 - обновить информацию о уже имеющейся, " +
                    " 2 - удалить задачу," +
                    " 3 - выполнить тест задачи," +
                    " любой другой символ - завершить работу");
            String answer2 = sc.next();   // выбор пользователя
            switch (answer2) {
                case "0": {
                    System.out.print("Введите номер программы от 00 до 99: ");
                    Integer id = sc.nextInt();  // считали номер программы
                    System.out.print("Введите имя программы: ");
                    String name = sc.next();   // считали имя программы
                    System.out.print("Введите описание программы: ");
                    String description = sc.next();  // считали описание программы
                    System.out.print("Введите исполнителя программы: ");
                    String executor = sc.next();
                    Task task = new Task(id, name, description, executor);   // создание новой задачи
                    try {

                        taskCache.add(task);   //  добавление задачи во множество
                        taskCache.writeObjects();  // буду постоянно обновлять файл для случая, когда программа
                    } catch (IOException e) {      // завершают работу не по сценарию, допустим свет выключили
                        System.out.println("Что-то пошло не так! Начинаем заново");
                        break;
                    }
                    System.out.println("Задача успешно добавлена! \n");
                    break;
                }
                case "1": {
                    System.out.println("Какую по счету задачу из промежутка [0, " + taskCache.size() + ") вы хотите обновить?");
                    int i = sc.nextInt();  // считали номер задачи
                    System.out.println("Какое поле хотите обновить? Доступные к изменению поля: name, description, executor. " +
                            "Во избежание ошибок введите первую букву имени поля");
                    String field = sc.next();  // считали первую букву имени поля
                    System.out.print("Введите информацию в выбранное поле: ");
                    String value = sc.next();  // считали информацию
                    switch (field) {   // Обновление данных в выбранной задаче.
                        case "n": {
                            taskCache.get(i).setName(value);
                            break;
                        }
                        case "d": {
                            taskCache.get(i).setDescription(value);
                            break;
                        }
                        case "e": {
                            taskCache.get(i).setExecutor(value);
                            break;
                        }
                        default: {
                            System.out.print("Вы все же допустили ошибку( Начинаем сначала");
                            break;
                        }
                    }
                    try {
                        taskCache.writeObjects();   //  обновление файла
                    } catch (IOException e) {
                        System.out.println("Данные не были добавлены.");
                    }

                    System.out.println("Задача успешно обновлена! \n");
                    break;
                }
                case "2": {
                    System.out.println("Какую по счету задачу из промежутка [0, " + taskCache.size() + ") вы хотите удалить?");
                    int i = sc.nextInt();   // считали номер задачи
                    if (i < taskCache.size()) {
                        taskCache.remove(i);   //  удаление выбранной задачи из множества
                        try {
                            taskCache.writeObjects();  //  обновление файла
                        } catch (IOException e) {
                            System.out.println("Задача не была добавлена.");
                        }
                        System.out.println("Задача успешно удалена! \n");
                        break;
                    }
                    System.out.println("Такая задача не существует \n");
                    break;
                }
                case "3": {
                    System.out.println("Какую по счету задачу из промежутка [0, " + taskCache.size() + ") вы хотите выполнить?");
                    int i = sc.nextInt();  // считали номер задачи
                    taskCache.get(i).getTest();   // запуск теста выбранной задачи
                    try {
                        taskCache.writeObjects();  //  обновление файла
                    } catch (IOException e) {
                        System.out.println("Возникли проблемы с тестом, статус заадчи не изменен");
                    }
                    System.out.println("Тест запущен! \n");


                }
                default: {
                    break;
                }
            }
            // культурное завершение программы
            if (!(answer2.equals("0") || answer2.equals("1") || answer2.equals("2") || answer2.equals("3"))) {
                System.out.println("До свидания!");
                break;
            }

        }
    }
}
