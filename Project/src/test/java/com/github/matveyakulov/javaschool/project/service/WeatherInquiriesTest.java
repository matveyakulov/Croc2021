package com.github.matveyakulov.javaschool.project.service;

import com.github.matveyakulov.javaschool.project.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherPresssure;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherTemperature;
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


/**
 * Тесты класса SqlService.
 */
public class WeatherInquiriesTest extends TestCase {

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
     * Связь с бд.
     */
    private WeatherInquiries service;

    /**
     * Начальная сборка.
     *
     * @throws IOException
     */
    public void setUp() throws IOException, SQLException {
        LocalDateTime dateTime1 = LocalDateTime.of(
                LocalDate.of(2020, 02, 02),
                LocalTime.of(21, 20));
        LocalDateTime dateTime2 = LocalDateTime.of(
                LocalDate.of(2020, 02, 02),
                LocalTime.of(22, 20));
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
            service.insert(weathersTemp.get(i));
        }

        Assertions.assertEquals(2, service.selectAll().size());

    }

    /**
     * Тест метода insert для обьекта WeatherPres.
     *
     * @throws SQLException
     */
    public void testInsertPres() throws SQLException {
        for (int i = 0; i < weathersPres.size(); i++) {
            service.insert(weathersPres.get(i));
        }

        Assertions.assertEquals(2, service.selectAll().size());

    }

    /**
     * Тест метода deleteAll.
     *
     * @throws SQLException
     */
    public void testDeleteAll() throws SQLException {

        for (int i = 0; i < weathersPres.size(); i++) {
            service.insert(weathersPres.get(i));
        }

        Assertions.assertEquals(2, service.selectAll().size());
        service.deleteAll();
        Assertions.assertEquals(0, service.selectAll().size());
    }

    /**
     * Тест метода exist.
     *
     * @throws SQLException
     */
    public void testExist() throws SQLException {
        service.insert(weathersPres.get(0));
        Assertions.assertEquals(true, service.exist(
                weathersPres.get(0).getCity(),
                weathersPres.get(0).getDatetime()
        ));
        Assertions.assertEquals(false, service.exist(
                weathersPres.get(1).getCity(),
                weathersPres.get(1).getDatetime()
        ));
    }

    /**
     * Тест метода select.
     *
     * @throws SQLException
     */
    public void testSelect() throws SQLException {
        LocalDateTime dateTime1 = LocalDateTime.of(
                LocalDate.of(2020, 02, 02),
                LocalTime.of(22, 00));
       LocalDateTime dateTime2 = LocalDateTime.of(
                LocalDate.of(2020, 02, 02),
                LocalTime.of(20, 00));
       for (int i = 0; i < weathersTemp.size(); i++) {
            service.insert(weathersTemp.get(i));
        }

        Assertions.assertEquals(1, service.select("LA", dateTime1).size());
        Assertions.assertEquals(0, service.select("LA", dateTime2).size());
    }

    /**
     * Тест метода update температуры.
     *
     * @throws SQLException
     */
    public void testUpdateTemp() throws SQLException {
        service.insert(weathersPres.get(0));
        Assertions.assertEquals(0.0, service.selectAll().get(1).getTemperature());
        Assertions.assertEquals(weathersPres.get(0).getDatetime(),
                service.selectAll().get(1).getDateTime().toLocalDateTime());
        service.update(weathersTemp.get(0));
        Assertions.assertEquals(weathersTemp.get(0).getTemperature(), service.selectAll().get(1).getTemperature());
        Assertions.assertEquals(weathersTemp.get(0).getDatetime(),
                service.selectAll().get(1).getDateTime().toLocalDateTime());
    }

    /**
     * Тест метода update давления.
     *
     * @throws SQLException
     */
    public void testUpdatePres() throws SQLException {
        service.insert(weathersTemp.get(0));
        Assertions.assertEquals(0.0, service.selectAll().get(1).getPressure());
        Assertions.assertEquals(weathersTemp.get(0).getDatetime(),
                service.selectAll().get(1).getDateTime().toLocalDateTime());
        service.update(weathersPres.get(0));
        Assertions.assertEquals(weathersPres.get(0).getPressure(), service.selectAll().get(1).getPressure());
        Assertions.assertEquals(weathersPres.get(0).getDatetime(),
                service.selectAll().get(1).getDateTime().toLocalDateTime());
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
