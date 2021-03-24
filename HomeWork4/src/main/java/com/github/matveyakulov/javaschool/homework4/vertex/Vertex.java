package com.github.matveyakulov.javaschool.homework4.vertex;

/**
 * Класс для вершины.
 */
public class Vertex<T> {

    /**
     * Идентификатор вершины.
     */
    private T id;

    /**
     * Имя вершины.
     */
    private String name;

    /**
     * Номер вершины.
     */
    private Integer num;

    public Vertex(Integer num, T id, String name) {
        this.num = num;
        this.id = id;
        this.name = name;
    }

    public Integer getNum() {
        return num;
    }
}
