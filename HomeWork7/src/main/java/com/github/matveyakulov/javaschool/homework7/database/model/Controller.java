package com.github.matveyakulov.javaschool.homework7.database.model;

import com.github.matveyakulov.javaschool.homework7.model.Car;


import java.util.List;

/**
 * Класс, содержащий функциональность бд.
 */
public abstract class Controller {

    /**
     * Добавляет обьект в таблицу.
     *
     * @param car обьект.
     */
    public abstract void create(Car car);

    /**
     * Читает обьект из таблицы.
     *
     * @param id номер строки.
     * @return обьект.
     */
    public abstract Car read(int id);

    /**
     * Обновляет строку новым обьектом.
     *
     * @param id  номер строки.
     * @param car обьект.
     */
    public abstract void update(int id, Car car);

    /**
     * Удаляет обьект из таблицы.
     *
     * @param id номер строки.
     */
    public abstract void delete(int id);

    /**
     * Возвращает коллекцию обьектов.
     *
     * @return все обьекты из таблицы.
     */
    public abstract List<Car> findAll();

    /**
     * Полностью очищает таблицу.
     */
    public abstract void deleteAll();


}
