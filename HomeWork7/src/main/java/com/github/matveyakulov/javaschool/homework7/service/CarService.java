package com.github.matveyakulov.javaschool.homework7.service;

import com.github.matveyakulov.javaschool.homework7.model.Car;

import java.util.List;
import java.util.Map;

/**
 * Прикладной сервис.
 */
public class CarService {

    /**
     * Обьект бд.
     */
    private CarDiler carDiler;

    public CarService(CarDiler carDiler) {
        this.carDiler = carDiler;
    }

    /**
     * Добавляет обьект в таблицу.
     *
     * @param car обьект.
     */
    public void create(Car car) {
        carDiler.create(car);
    }

    /**
     * Вставляет обьект в заданную строку.
     *
     * @param id номер строки.
     * @param car обьект.
     */
    public void create(int id, Car car){
        carDiler.create(id, car);
    }

    /**
     * Читает обьект из таблицы.
     *
     * @param id номер строки.
     * @return обьект.
     */
    public Car read(int id) {
        return carDiler.read(id);
    }

    /**
     * Обновляет строку новым обьектом.
     *
     * @param id  номер строки.
     * @param car обьект.
     */
    public void update(int id, Car car) {
        carDiler.update(id, car);
    }

    /**
     * Удаляет обьект из таблицы.
     *
     * @param id номер строки.
     */
    public void delete(int id) {
        carDiler.delete(id);
    }

    /**
     * Возвращает коллекцию обьектов.
     *
     * @return все обьекты из таблицы.
     */
    public void deleteAll() {
        carDiler.deleteAll();
    }

    /**
     * Полностью очищает таблицу.
     */
    public Map<Integer, Car> findAll() {
        return carDiler.findAll();
    }
}
