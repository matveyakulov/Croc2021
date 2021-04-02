package com.github.matveyakulov.javaschool.homework5.manager;

import com.github.matveyakulov.javaschool.homework5.model.Task;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;
import java.io.IOException;

/**
 * Тест класса TaskCache.
 */
public class TaskCacheTest extends TestCase {  // в тестовом файле в данный момент 1 задача

    /**
     * Создание множества.
     */
    private TaskCache taskCache;

    /**
     * Путь к файлу.
     */
    private String pathFile = "src/test/java/com/github/matveyakulov/javaschool/homework5/tasksTest.txt";

    /**
     * Начальная сборка.
     */
    public void setUp() {
        taskCache = new TaskCache(pathFile);
        taskCache.add(new Task(11, "qweasd", "rewq", "rvwce"));

    }

    /**
     * Тест метода add.
     **/
    public void testAddTask() {
        taskCache.add(new Task(55, "qasd", "rewqfdvc", "rvwvdsace"));
        Assertions.assertEquals(2, taskCache.size());

    }

    /**
     * Тест метода remove.
     */
    public void testRemoveTask() {
        taskCache.remove(0);
        Assertions.assertEquals(0, taskCache.size());
    }

    /**
     * Тест метода size.
     */
    public void testSize() {
        Assertions.assertEquals(1, taskCache.size());
    }

    /**
     * Тест методов readObjects и writeObjects.
     *
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testWriteAndReadObjects() throws IOException, ClassNotFoundException {
        taskCache.writeObjects();
        taskCache.readObjects();
        Assertions.assertEquals(1, taskCache.size());
    }
}
