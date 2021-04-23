package com.github.matveyakulov.javaschool.homework7.service;

import com.github.matveyakulov.javaschool.homework7.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.homework7.model.Car;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Тесты для класса CarDiler.
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
        dataSourceProvider = new DataSourceProvider("application.properties");
        carDiler = new CarDiler(dataSourceProvider.getDataSource());
        car = new Car(612, "A666АА23", false, "2020-10-22", "12:00:00");
        car1 = new Car(12, "A666АА23", false, "2010-10-02", "23:00:03");

    }

    /**
     * Тест метода create с одним параметром.
     */
    public void testCreate1Param() {
        carDiler.deleteAll();    // очищаю, потому что не знаю, что там было до этого
        carDiler.create(car1);
        carDiler.create(car);
        Map<Integer, Car> carList = carDiler.findAll();
        Set<Integer> keySet = carList.keySet();
        System.out.println("You have:");
        for (Integer key : keySet) {
            System.out.println(key + " " + carList.get(key));
        }
        Assertions.assertEquals(2, carList.size());

    }

    /**
     * Тест метода create с двумя параметрами.
     */
    public void testCreate2Param() {
        carDiler.deleteAll();
        carDiler.create(2, car1);
        carDiler.create(1, car);
        Map<Integer, Car> carList = carDiler.findAll();
        Set<Integer> keySet = carList.keySet();
        System.out.println("You have:");
        for (Integer key : keySet) {
            System.out.println(key + " " + carList.get(key));
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
        Map<Integer, Car> carList = carDiler.findAll();
        System.out.println("Before");
        Set<Integer> keySet = carList.keySet();
        for (Integer key : keySet) {
            System.out.println(key + " " + carList.get(key));
        }
        Assertions.assertEquals(2, carList.size());
        carDiler.delete(1);
        carList = carDiler.findAll();
        System.out.println("After:");
        keySet = carList.keySet();
        for (Integer key : keySet) {
            System.out.println(key + " " + carList.get(key));
        }
        Assertions.assertEquals(1, carList.size());


    }

    /**
     * Тест метода  update.
     */
    public void testUpdate() {

        carDiler.deleteAll();
        carDiler.create(car);
        carDiler.create(car1);
        Map<Integer, Car> carList = carDiler.findAll();
        System.out.println("Before:");
        Set<Integer> keySet = carList.keySet();
        for (Integer key : keySet) {
            System.out.println(key + " " + carList.get(key));
        }
        Car car = new Car(300, "qwe", false, "2019-02-05", "10:45:15");
        carDiler.update(1, car);
        Assertions.assertEquals(car, carDiler.read(1));
        carList = carDiler.findAll();
        System.out.println("After:");
        keySet = carList.keySet();
        for (Integer key : keySet) {
            System.out.println(key + " " + carList.get(key));
        }

    }

    /**
     * Тест метода  deleteAll.
     */
    public void testDeleteAll() {
        carDiler.deleteAll();
        carDiler.create(car1);
        carDiler.create(car);
        Map<Integer, Car> carList = carDiler.findAll();
        Assertions.assertEquals(2, carList.size());
        carDiler.deleteAll();
        carList = carDiler.findAll();
        Assertions.assertEquals(0, carList.size());

    }


}
