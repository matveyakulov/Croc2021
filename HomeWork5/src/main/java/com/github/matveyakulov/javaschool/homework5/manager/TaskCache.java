package com.github.matveyakulov.javaschool.homework5.manager;

import com.github.matveyakulov.javaschool.homework5.model.Task;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Множество задач.
 */
public class TaskCache {

    /**
     * Список задач.
     */
    private List<Task> taskList;

    /**
     * Путь к файлу, с которым работаем.
     */
    private String pathFile;


    public TaskCache(String path) {
        taskList = new ArrayList<>();
        pathFile = path;
    }

    /**
     * Добавление задачи во множество.
     *
     * @param task задача,которую нужно добавить.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Удаление задачи из множества.
     *
     * @param i номер задачи, которую нужно удалить.
     */
    public void remove(int i) {
        taskList.remove(i);
    }

    /**
     * Возвращает размер кэша.
     *
     * @return размер кэша.
     */
    public int size() {
        return taskList.size();
    }

    /**
     * @param i номер задачи.
     * @return возвращает задачу.
     */
    public Task get(int i) {
        return taskList.get(i);
    }

    /**
     * Записывает все обьекты из множества в файл.
     *
     * @throws IOException
     */
    public void writeObjects() throws IOException {

        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(pathFile))) {
            ois.writeObject(taskList);
        }
    }

    /**
     * Считывает список обьектов из файла.
     */
    public void readObjects() throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathFile))) {
            taskList = (ArrayList<Task>) ois.readObject();
        }


    }

}
