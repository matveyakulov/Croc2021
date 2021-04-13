package com.github.matveyakulov.javaschool.homework7.service;

import com.github.matveyakulov.javaschool.homework7.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.homework7.model.Car;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.List;

/**
 * Тесты для класса CarService.
 */
public class CarServiceTest extends TestCase {

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
     * Экземпляр класса CarService.
     */
    private CarService carService;

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
        carDiler.create(car);
        carDiler.create(car1);
        carService = new CarService(carDiler);

    }

    /**
     * Тест метода create.
     */
    public void testCreate() {
        carService.deleteAll();
        carService.create(car1);
        carService.create(car);
        List<Car> carList = carService.findAll();
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
        carService.deleteAll();
        carService.create(car1);
        carService.create(car);

        Assertions.assertEquals(car, carService.read(2));
    }

    /**
     * Тест метода delete.
     */
    public void testDelete() {
        carService.deleteAll();
        carService.create(car);
        carService.create(car1);
        List<Car> carList = carService.findAll();
        System.out.println("After");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        Assertions.assertEquals(2, carList.size());
        carService.delete(1);
        carList = carService.findAll();
        System.out.println("Before:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        Assertions.assertEquals(1, carList.size());


    }

    /**
     * Тест метода update.
     */
    public void testUpdate() {

        List<Car> carList = carService.findAll();
        System.out.println("After:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }
        Car car = new Car(300, "qwe", false, "2019-02-05", "10:45:15");
        carService.update(2, car);

        carList = carService.findAll();
        System.out.println("Before:");
        for (int i = 0; i < carList.size(); i++) {
            System.out.println(carList.get(i));
        }

    }

    /**
     * Тест метода deleteAll.
     */
    public void testDeleteAll() {
        carService.deleteAll();
        carService.create(car1);
        carService.create(car);
        List<Car> carList = carService.findAll();
        Assertions.assertEquals(2, carList.size());
        carService.deleteAll();
        carList = carService.findAll();
        Assertions.assertEquals(0, carList.size());

    }

}
