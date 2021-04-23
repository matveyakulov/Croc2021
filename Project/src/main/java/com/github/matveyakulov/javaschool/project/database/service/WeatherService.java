package com.github.matveyakulov.javaschool.project.database.service;

import com.github.matveyakulov.javaschool.project.model.Weather;
import com.github.matveyakulov.javaschool.project.model.WeatherPres;
import com.github.matveyakulov.javaschool.project.model.WeatherTemp;

import java.sql.SQLException;
import java.util.Map;

/**
 * Прикладной сервис.
 */
public class WeatherService {

    /**
     * Связь с бд.
     */
    SqlService service;

    public WeatherService(SqlService service) {
        this.service = service;
    }

    /**
     * Вставляет обьект WeatherTemp в бд.
     *
     * @param weather обьект.
     * @throws SQLException
     */
    public void insert(WeatherTemp weather) throws SQLException {
        if (!service.exist(weather.getCity(), weather.getDatetime())) {
            service.insert(weather);
        } else {
            service.update(weather);
        }
    }

    /**
     * Вставляет обьект WeatherPres в бд.
     *
     * @param weather обьект.
     * @throws SQLException
     */
    public void insert(WeatherPres weather) throws SQLException {

        if (!service.exist(weather.getCity(), weather.getDatetime())) {
            service.insert(weather);
        } else {
            service.update(weather);
        }

    }


    /**
     * Выбирает все обьекты из бд.
     *
     * @return обьекты
     * @throws SQLException
     */
    public Map<Integer, Weather> selectAll() throws SQLException {
        return service.selectAll();
    }

    /**
     * Очищает таблицу.
     */
    public void deleteAll() throws SQLException {
        service.deleteAll();
    }

    /**
     * Сортирует записи бд по городу, а потом по дате и времени.
     *
     * @throws SQLException
     */
    public void sort() throws SQLException {
        service.sort();
    }
}
