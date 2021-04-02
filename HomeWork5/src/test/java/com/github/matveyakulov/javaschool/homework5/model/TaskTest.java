package com.github.matveyakulov.javaschool.homework5.model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

/**
 * Тесты для класса Task.
 */
public class TaskTest extends TestCase {

    /**
     * Задача.
     */
    private Task task;

    /**
     * Создание задачи.
     */
    public void setUp() {
        task = new Task(11, "qwe", "asd", "zxc");
    }

    /**
     * Тест построения метода buildCode.
     */
    public void testBuildCode() {
        Assertions.assertEquals("package com.github.matveyakulov.javaschool.homework5.model;" + "\n" +
                "import java.util.*;\n" +
                "String s = \"Name\";col+=100;\n" +
                "if(col != 105){\n" +
                "System.out.println(\"Error!\");\n" +
                "}", task.getCode());
    }

    /**
     * Тест метода getTest.
     */
    public void testGetTest() {
        task.getTest();
        Assertions.assertEquals(TaskStatus.SUCCESS, task.getStatus());
    }
}
