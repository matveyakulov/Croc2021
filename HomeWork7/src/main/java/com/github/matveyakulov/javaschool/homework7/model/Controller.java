package com.github.matveyakulov.javaschool.homework7.model;

import java.util.Map;

/**
 * Описывает функционал запросов к бд.
 */
public abstract class Controller {

    /**
     * Создает новую запись в таблице.
     *
     * @param car машина.
     */
    public abstract void create(Car car);

    /**
     * Создает новую запись по указанному индексу.
     *
     * @param id номер строки.
     * @param car машина.
     */
    public abstract void create(int id, Car car);

    /**
     * Читает заданную строку.
     *
     * @param id номер строки.
     * @return машина.
     */
    public abstract Car read(int id);

    /**
     * Обновляет заданную строку.
     *
     * @param id номер строки.
     * @return машина.
     */
    public abstract void update(int id, Car car);

    /**
     * Обновляет заданную строку.
     *
     * @param id номер строки.
     */
    public abstract void delete(int id);

    /**
     * Возвращает список со всеми элементами.
     *
     * @return все элементы таблицы.
     */
    public abstract Map<Integer, Car> findAll();

    /**
     * Удаляет все записи.
     */
    public abstract void deleteAll();


}
