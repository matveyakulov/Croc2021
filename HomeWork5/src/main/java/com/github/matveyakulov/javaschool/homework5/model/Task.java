package com.github.matveyakulov.javaschool.homework5.model;

import java.io.Serializable;

/**
 * Задача.
 */
public class Task implements Serializable {

    /**
     * Поле сериализации.
     */
    private static final long serialVersionUid = 1L;

    /**
     * Идентификатор, по которому будем строить задачу.
     */
    private final Integer id;

    /**
     * Код задачи.
     */
    private final String code;

    /**
     * Имя задачи.
     */
    private String name;

    /**
     * Имя задачи.
     */
    private String description;

    /**
     * исполнитель задачи.
     */
    private String executor;

    /**
     * Статус задачи.
     */
    private TaskStatus status;


    public Task(Integer id, String name, String description, String executor) {
        this.id = id;
        code = buildCode(id);
        this.name = name;
        this.description = description;
        this.executor = executor;
        status = TaskStatus.NOT_CHECKED;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getExecutor() {
        return executor;
    }

    public TaskStatus getStatus() {
        return status;
    }

    /**
     * Строит код задачи.
     *
     * @return возвращает код задачи.
     */
    private String buildCode(int id) { // собираем код по структуре правильный, а по работе как повезет
        String settings = "package com.github.matveyakulov.javaschool.homework5.model;"
                + "\n" + "import java.util.*;" + "\n";

        String[] initialization = new String[]{"String s = \"Name\";", "int col = 5;", "double num = 5.78;"};

        String[] body = new String[]{
                "col+=100;\n" +
                        "if(col != 105){\n" +
                        "System.out.println(\"Error!\");\n" + "}",

                "s+=\" Lastname\";\n" +
                        "if(!s.equals(\"Name Lastname\")){\n" +
                        "System.out.println(\"Error!\");\n" + "}",

                "num+= 15.22;\n" +
                        "if(num != 21){\n" +
                        "System.out.println(\"Error!\");\n" + "}"

        };

        StringBuilder code = new StringBuilder(settings);
        switch (id / 10) {
            case 0:
            case 1:
            case 2:
            case 3:
                code.append(initialization[0]);
                break;
            case 4:
            case 5:
            case 6:
                code.append(initialization[1]);
                break;
            case 7:
            case 8:
            case 9:
                code.append(initialization[2]);
        }
        switch (id % 10) {
            case 0:
            case 1:
            case 2:
            case 3:
                code.append(body[0]);
                break;
            case 4:
            case 5:
            case 6:
                code.append(body[1]);
                break;
            case 7:
            case 8:
            case 9:
                code.append(body[2]);

        }
        return code.toString();
    }

    /**
     * Запускает тест задачи.
     */
    public void getTest() {
        if (id >= 00 && id <= 33 || id >= 44 && id <= 66 || id >= 77 && id <= 99) {
            status = TaskStatus.SUCCESS;
        } else
            status = TaskStatus.ERROR;
    }

    @Override
    public String toString() {
        return "Task{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", executor='" + executor + '\'' +
                ", status=" + status +
                '}';
    }


}
