package com.github.matveyakulov.javaschool.homework7.service;

import com.github.matveyakulov.javaschool.homework7.model.Car;

import java.util.List;
import java.util.Map;

/**
 * Прикладной сервис.
 */
public class CarService {

    /**
     * Запросы к бд.
     */
    private CarDiler carDiler;

    public CarService(CarDiler carDiler) {
        this.carDiler = carDiler;
    }

    /**
     * Создает новую запись в таблице.
     *
     * @param car машина.
     */
    public void create(Car car) {
        carDiler.create(car);
    }

    /**
     * Создает новую запись по указанному индексу.
     *
     * @param id номер строки.
     * @param car машина.
     */
    public void create(int id, Car car){
        carDiler.create(id, car);
    }

    /**
     * Читает заданную строку.
     *
     * @param id номер строки.
     * @return машина.
     */
    public Car read(int id) {
        return carDiler.read(id);
    }

    /**
     * Обновляет заданную строку.

     *
     * @param id номер строки.
     * @return машина.
     */
    public void update(int id, Car car) {
        carDiler.update(id, car);
    }

    /**
     * Обновляет заданную строку.
     *
     * @param id номер строки.
     */
    public void delete(int id) {
        carDiler.delete(id);
    }

    /**
     * Возвращает список со всеми элементами.
     *
     * @return все элементы таблицы.
     */
    public void deleteAll() {
        carDiler.deleteAll();
    }

    /**
     * Удаляет все записи.
     */
    public Map<Integer, Car> findAll() {
        return carDiler.findAll();
    }
}
