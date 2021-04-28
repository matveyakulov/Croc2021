package com.github.matveyakulov.javaschool.project.service;

import com.github.matveyakulov.javaschool.project.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherPresssure;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherTemperature;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Тесты класса WeatherService.
 */
public class WeatherServiceTest extends TestCase {

    /**
     * Прикладной сервис.
     */
    private WeatherService weatherService;

    /**
     * Data source.
     */
    private DataSourceProvider dataSource;

    /**
     * Список погоды с температурой.
     */
    private Weathers<WeatherTemperature> weathersTemp;

    /**
     * Список погоды с давлением.
     */
    private Weathers<WeatherPresssure> weathersPres;
    /**
     * Связь  с бд.
     */
    private WeatherInquiries service;

    /**
     * Начальная сборка.
     *
     * @throws IOException
     */
    public void setUp() throws IOException {
        LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.parse("21:20"));
        LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.parse("22:20"));
        weathersTemp = new Weathers<>(Arrays.asList(
                new WeatherTemperature(
                        "LA", dateTime1, 22),
                new WeatherTemperature(
                        "Cali", dateTime2, 25)
        ));
        weathersPres = new Weathers<>(Arrays.asList(
                new WeatherPresssure(
                        "LA", dateTime2, 22),
                new WeatherPresssure(
                        "Cali", dateTime1, 30)
        ));
        dataSource = new DataSourceProvider("app.properties");
        try {
            service = new WeatherInquiries(dataSource.getDataSource());
        } catch (SQLException throwables) {
            System.out.println("Таблица не создана, обновите данные и повторите");
            System.exit(0);
        }
        weatherService = new WeatherService(service);
    }

    /**
     * Очищает таблицу после каждого теста.
     *
     * @throws SQLException
     */
    public void tearDown() throws SQLException {
        service.deleteAll();
    }

    /**
     * Тест метода insert для обьекта WeatherTemp.
     *
     * @throws SQLException
     */
    public void testInsertTemp() throws SQLException {
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
        for (int i = 0; i < weathersPres.size(); i++) {
            service.insert(weathersPres.get(i));
        }
        for (int i = 0; i < weathersTemp.size(); i++) {
            service.insert(weathersTemp.get(i));
        }
        service.sort();
    }
}
