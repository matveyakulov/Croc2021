package com.github.matveyakulov.javaschool.homework7.service;

import com.github.matveyakulov.javaschool.homework7.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.homework7.model.Car;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.List;

/**
 * Класс тестов для CarDiler.
 */
public class CarDilerTest extends TestCase {

    /**
     * Data Source.
     */
    private DataSourceProvider dataSourceProvider;
    /**
     * Экземпляр класса CarDiler.
     */
    private CarDiler carDiler;
    /**
     * Машина.
     */
    private Car car;
    /**
     * Машина.
     */
    private Car car1;

    /**
     * Начальная сборка.
     *
     * @throws IOException
     */
    public void setUp() throws IOException {
        dataSourceProvider = new DataSourceProvider();
        carDiler = new CarDiler(dataSourceProvider.getDataSource());
        car = new Car(612, "A666АА23", false, "2020-10-22", "12:00:00");
        car1 = new Car(12, "A666АА23", false, "2010-10-02", "23:00:03");

    }

    /**
     * Тест метода create.
     */
    public void testCreate() {
        carDiler.deleteAll();    // очистил таблицу, иначе я дальше никак не угадаю колво элементов в ней
        carDiler.create(car1);
        carDiler.create(car);
        List<Car> carList = carDiler.findAll();
        System.out.println("You have:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        Assertions.assertEquals(2, carList.size());

    }

    /**
     * Тест метода read.
     */
    public void testRead() {
        carDiler.deleteAll();
        carDiler.create(car1);
        carDiler.create(car);

        Assertions.assertEquals(car, carDiler.read(2));
    }

    /**
     * Тест метода delete.
     */
    public void testDelete() {
        carDiler.deleteAll();
        carDiler.create(car);
        carDiler.create(car1);
        List<Car> carList = carDiler.findAll();
        System.out.println("Before");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        Assertions.assertEquals(2, carList.size());
        carDiler.delete(1);
        carList = carDiler.findAll();
        System.out.println("After:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        Assertions.assertEquals(1, carList.size());


    }

    /**
     * Тест метода update.
     */
    public void testUpdate() {

        List<Car> carList = carDiler.findAll();
        System.out.println("Before:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        Car car = new Car(300, "qwe", false, "2019-02-05", "10:45:15");
        carDiler.update(2, car);
        Assertions.assertEquals(car, carDiler.read(2));
        carList = carDiler.findAll();
        System.out.println("After:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }

    }

    /**
     * Тест метода deleteAll.
     */
    public void testDeleteAll() {
        carDiler.deleteAll();
        carDiler.create(car1);
        carDiler.create(car);
        List<Car> carList = carDiler.findAll();
        Assertions.assertEquals(2, carList.size());
        carDiler.deleteAll();
        carList = carDiler.findAll();
        Assertions.assertEquals(0, carList.size());

    }


}
