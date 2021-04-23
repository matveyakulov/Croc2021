package com.github.matveyakulov.javaschool.project.database.service;

import com.github.matveyakulov.javaschool.project.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.project.model.Weather;
import com.github.matveyakulov.javaschool.project.model.WeatherPres;
import com.github.matveyakulov.javaschool.project.model.WeatherTemp;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Map;

/**
 * Тесты класса WeatherService.
 */
public class WeatherServiceTest extends TestCase {

    /**
     * Прикладной сервис.
     */
    WeatherService weatherService;

    /**
     * Data source.
     */
    DataSourceProvider dataSource;

    /**
     * Список погоды с температурой.
     */
    Weathers<WeatherTemp> weathersTemp;

    /**
     * Список погоды с давлением.
     */
    Weathers<WeatherPres> weathersPres;
    /**
     * Связь  с бд.
     */
    SqlService service;

    /**
     * Начальная сборка.
     *
     * @throws IOException
     */
    public void setUp() throws IOException {
        LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.parse("21:20"));
        LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.parse("22:20"));
        weathersTemp = new Weathers<>(Arrays.asList(
                new WeatherTemp(
                        "LA", Timestamp.valueOf(dateTime1), 22),
                new WeatherTemp(
                        "Cali", Timestamp.valueOf(dateTime2), 25)
        ));
        weathersPres = new Weathers<>(Arrays.asList(
                new WeatherPres(
                        "LA", Timestamp.valueOf(dateTime2), 22),
                new WeatherPres(
                        "Cali", Timestamp.valueOf(dateTime1), 30)
        ));
        dataSource = new DataSourceProvider("app.properties");
        service = new SqlService(dataSource.getDataSource());
        weatherService = new WeatherService(service);
    }

    /**
     * Тест метода insert для обьекта WeatherTemp.
     *
     * @throws SQLException
     */
    public void testInsertTemp() throws SQLException {
        weatherService.deleteAll();
        for (int i = 0; i < weathersTemp.size(); i++) {
            weatherService.insert(weathersTemp.get(i));
        }

        Assertions.assertEquals(2, weatherService.selectAll().size());

    }

    /**
     * Тест метода insert для обьекта WeatherPres.
     *
     * @throws SQLException
     */
    public void testInsertPres() throws SQLException {
        weatherService.deleteAll();
        for (int i = 0; i < weathersPres.size(); i++) {
            weatherService.insert(weathersPres.get(i));
        }

        Assertions.assertEquals(2, weatherService.selectAll().size());

    }

    /**
     * Тест метода deleteAll.
     *
     * @throws SQLException
     */
    public void testDeleteAll() throws SQLException {

        weatherService.deleteAll();
        for (int i = 0; i < weathersPres.size(); i++) {
            weatherService.insert(weathersPres.get(i));
        }
        Assertions.assertEquals(2, weatherService.selectAll().size());
        weatherService.deleteAll();
        Assertions.assertEquals(0, weatherService.selectAll().size());
    }

    /**
     * Тест метода sort.
     *
     * @throws SQLException
     */
    public void testSort() throws SQLException {
        service.deleteAll();
        for (int i = 0; i < weathersPres.size(); i++) {
            service.insert(weathersPres.get(i));
        }
        for (int i = 0; i < weathersTemp.size(); i++) {
            service.insert(weathersTemp.get(i));
        }
        System.out.println("Before sort: ");
        Map<Integer, Weather> weatherMapBefore = weatherService.selectAll();
        for (Integer key : weatherMapBefore.keySet()) {
            System.out.println(key + " " + weatherMapBefore.get(key));
        }
        service.sort();
        System.out.println("After sort: ");
        Map<Integer, Weather> weatherMapAfter = weatherService.selectAll();
        for (Integer key : weatherMapAfter.keySet()) {
            System.out.println(key + " " + weatherMapAfter.get(key));
        }
    }
}
