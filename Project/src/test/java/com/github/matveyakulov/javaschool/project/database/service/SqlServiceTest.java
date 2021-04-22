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


public class SqlServiceTest extends TestCase {

    /**
     * Data source.
     */
    DataSourceProvider dataSource;

    /**
     * ������ ����� � ������������.
     */
    Weathers<WeatherTemp> weathersTemp;

    /**
     * ������ ����� � ���������.
     */
    Weathers<WeatherPres> weathersPres;
    /**
     * ����� � ��.
     */
    SqlService service;

    /**
     * ��������� ������.
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
    }

    /**
     * ���� ������ insert ��� ������� WeatherTemp.
     *
     * @throws SQLException
     */
    public void testInsertTemp() throws SQLException {
        service.deleteAll();
        for (int i = 0; i < weathersTemp.size(); i++) {
            service.insert(weathersTemp.get(i));
        }

        Assertions.assertEquals(2, service.selectAll().size());

    }

    /**
     * ���� ������ insert ��� ������� WeatherPres.
     *
     * @throws SQLException
     */
    public void testInsertPres() throws SQLException {
        service.deleteAll();
        for (int i = 0; i < weathersPres.size(); i++) {
            service.insert(weathersPres.get(i));
        }

        Assertions.assertEquals(2, service.selectAll().size());

    }

    /**
     * ���� ������ deleteAll.
     *
     * @throws SQLException
     */
    public void testDeleteAll() throws SQLException {

        service.deleteAll();
        for (int i = 0; i < weathersPres.size(); i++) {
            service.insert(weathersPres.get(i));
        }
        Assertions.assertEquals(2, service.selectAll().size());
        service.deleteAll();
        Assertions.assertEquals(0, service.selectAll().size());
    }

    /**
     * ���� ������ exist.
     *
     * @throws SQLException
     */
    public void testExist() throws SQLException {
        service.deleteAll();
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
     * ���� ������ select.
     *
     * @throws SQLException
     */
    public void testSelect() throws SQLException {
        service.deleteAll();
        LocalDateTime dateTime1 = LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.parse("22:00"));
        LocalDateTime dateTime2 = LocalDateTime.of(LocalDate.parse("2020-02-02"), LocalTime.parse("20:00"));
        for (int i = 0; i < weathersTemp.size(); i++) {
            service.insert(weathersTemp.get(i));
        }

        Assertions.assertEquals(1, service.select("LA", Timestamp.valueOf(dateTime1)).size());
        Assertions.assertEquals(0, service.select("LA", Timestamp.valueOf(dateTime2)).size());
    }

    /**
     * ���� ������ update �����������.
     *
     * @throws SQLException
     */
    public void testUpdateTemp() throws SQLException {
        service.deleteAll();
        service.insert(weathersPres.get(0));
        Assertions.assertEquals(0.0, service.selectAll().get(1).getTemperature());
        Assertions.assertEquals(weathersPres.get(0).getDatetime(), service.selectAll().get(1).getDateTime());
        service.update(weathersTemp.get(0));
        Assertions.assertEquals(weathersTemp.get(0).getTemperature(), service.selectAll().get(1).getTemperature());
        Assertions.assertEquals(weathersTemp.get(0).getDatetime(), service.selectAll().get(1).getDateTime());
    }

    /**
     * ���� ������ update ��������.
     *
     * @throws SQLException
     */
    public void testUpdatePres() throws SQLException {
        service.deleteAll();
        service.insert(weathersTemp.get(0));
        Assertions.assertEquals(0.0, service.selectAll().get(1).getPressure());
        Assertions.assertEquals(weathersTemp.get(0).getDatetime(), service.selectAll().get(1).getDateTime());
        service.update(weathersPres.get(0));
        Assertions.assertEquals(weathersPres.get(0).getPressure(), service.selectAll().get(1).getPressure());
        Assertions.assertEquals(weathersPres.get(0).getDatetime(), service.selectAll().get(1).getDateTime());
    }

    /**
     * ���� ������ sort.
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
        Map<Integer, Weather> weatherMapBefore = service.selectAll();
        for (Integer key : weatherMapBefore.keySet()) {
            System.out.println(key + " " + weatherMapBefore.get(key));
        }
        service.sort();
        System.out.println("After sort: ");
        Map<Integer, Weather> weatherMapAfter = service.selectAll();
        for (Integer key : weatherMapAfter.keySet()) {
            System.out.println(key + " " + weatherMapAfter.get(key));
        }
    }

}
