package com.github.matveyakulov.javaschool.project.service;

import com.github.matveyakulov.javaschool.project.model.Weather;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherPresssure;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherTemperature;
import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * Запросы к бд.
 */
public class WeatherInquiries {

    /**
     * Название таблицы.
     */
    private final String TABLE_NAME = "weather";

    /**
     * Data Source.
     */
    private EmbeddedDataSource dataSource;

    public WeatherInquiries(EmbeddedDataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Создание таблицы, если нет.
     */
    private void initTable() throws SQLException {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (!resultSet.next()) {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                                + "city VARCHAR(10), "
                                + "datetime TIMESTAMP, "
                                + "temperature DOUBLE PRECISION, "
                                + "pressure DOUBLE PRECISION"
                                + ")");
            }
        }finally {
            System.setProperty("derby.language.sequence.preallocator", "1");
        }
    }

    /**
     * Вставляет обьект WeatherTemp в бд.
     *
     * @param weather обьект WeatherTemp.
     */
    public void insert(WeatherTemperature weather) throws SQLException {

        final String sqlQuery = "INSERT INTO " + TABLE_NAME + " (city, datetime, temperature, pressure)"
                + " VALUES(?, ?, ?, 0)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, weather.getCity());
            statement.setTimestamp(2, Timestamp.valueOf(weather.getDatetime()));
            statement.setDouble(3, weather.getTemperature());

            statement.executeUpdate();
        }

    }

    /**
     * Вставляет обьект WeatherPres в бд.
     *
     * @param weather обьект WeatherPres.
     */
    public void insert(WeatherPresssure weather) throws SQLException {

        final String sqlQuery = "INSERT INTO " + TABLE_NAME + " (city, datetime, temperature, pressure)"
                + " VALUES(?, ?, 0, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, weather.getCity());
            statement.setTimestamp(2, Timestamp.valueOf(weather.getDatetime()));
            statement.setDouble(3, weather.getPressure());
            statement.executeUpdate();
        }

    }

    /**
     * Вставляет обьект WeatherPres в бд.
     *
     * @param weather обьект Weather.
     */
    private void insert(Weather weather) throws SQLException {

        final String sqlQuery = "INSERT INTO " + TABLE_NAME + " (city, datetime, temperature, pressure)"
                + " VALUES(?, ?, ?, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, weather.getCity());
            statement.setTimestamp(2, weather.getDateTime());
            statement.setDouble(3, weather.getTemperature());
            statement.setDouble(4, weather.getPressure());
            statement.executeUpdate();
        }

    }

    /**
     * Выбирает все обьекты из бд.
     *
     * @return обьекты.
     */
    public Map<Integer, Weather> selectAll() throws SQLException {
        final String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Map<Integer, Weather> weatherMap = new HashMap();
            while (resultSet.next()) {
                weatherMap.put(resultSet.getInt(1),
                        new Weather(
                                resultSet.getString(2),
                                resultSet.getTimestamp(3),
                                resultSet.getDouble(4),
                                resultSet.getDouble(5)
                        ));
            }
            resultSet.close();
            return weatherMap;
        }
    }

    /**
     * Очищает таблицу.
     */
    public void deleteAll() throws SQLException {
        final String sqlQuery = "DROP TABLE " + TABLE_NAME; // чтобы инкремент с 1 начинался

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
            initTable();
        }


    }

    /**
     * Проверяет, есть ли в таблице обьект с указанным городом и датой из промежутка.
     *
     * @param city город.
     * @param dateTime дата и время.
     * @return false - ничего не найдено, true - иначе.
     * @throws SQLException
     */
    public boolean exist(String city, LocalDateTime dateTime) throws SQLException {

        Timestamp timestampMinus = Timestamp.valueOf(dateTime.plusHours(-1));
        Timestamp timestampPlus = Timestamp.valueOf(dateTime.plusHours(1));
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE city = ?" +
                "AND (datetime BETWEEN ? AND ?)";
        ResultSet rs = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, city);
            statement.setTimestamp(2, timestampMinus);
            statement.setTimestamp(3, timestampPlus);
            rs = statement.executeQuery();

            return rs.next();
        }
        finally {
            rs.close();
        }
    }

    /**
     * Выбирает обьекты с заданным городом и временем из промежутка.
     *
     * @param city  город.
     * @param dateTime дата и время.
     * @return обьекты.
     * @throws SQLException
     */
    public Map<Integer, Weather> select(String city, LocalDateTime dateTime) throws SQLException {
        Timestamp timestampMinus = Timestamp.valueOf(dateTime.plusHours(-1));
        Timestamp timestampPlus = Timestamp.valueOf(dateTime.plusHours(1));
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE city = ?" +
                "AND (datetime BETWEEN ? AND ?)";
        Map<Integer, Weather> weatherMap = new HashMap<>();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, city);
            statement.setTimestamp(2, timestampMinus);
            statement.setTimestamp(3, timestampPlus);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                weatherMap.put(rs.getInt(1),
                        new Weather(rs.getString(2),
                                rs.getTimestamp(3),
                                rs.getDouble(4),
                                rs.getDouble(5)));
            }
            rs.close();
            return weatherMap;
        }
    }

    /**
     * Обновляет время и температуру у обьекта.
     *
     * @param weather обьект.
     * @throws SQLException
     */
    public void update(WeatherTemperature weather) throws SQLException {

        final String sqlQueryUpdate = "UPDATE " + TABLE_NAME + " SET datetime = ?, temperature = ?" +
                "WHERE id = ?";

        Map<Integer, Weather> weatherMap = select(weather.getCity(), weather.getDatetime());
        for (Integer key : weatherMap.keySet()) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlQueryUpdate)) {

                preparedStatementUpdate.setTimestamp(1, Timestamp.valueOf(weather.getDatetime()));
                preparedStatementUpdate.setDouble(2, weather.getTemperature());
                preparedStatementUpdate.setInt(3, key);
                preparedStatementUpdate.executeUpdate();
            }
        }

    }

    /**
     * Обновляет время и давление у обьекта.
     *
     * @param weather обьект.
     * @throws SQLException
     */
    public void update(WeatherPresssure weather) throws SQLException {

        final String sqlQueryUpdate = "UPDATE " + TABLE_NAME + " SET datetime = ?, pressure = ? " +
                "WHERE id = ?";
        Map<Integer, Weather> weatherMap = select(weather.getCity(), weather.getDatetime());
        for (Integer key : weatherMap.keySet()) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlQueryUpdate)) {

                preparedStatementUpdate.setTimestamp(1, Timestamp.valueOf(weather.getDatetime()));
                preparedStatementUpdate.setDouble(2, weather.getPressure());
                preparedStatementUpdate.setInt(3, key);
                preparedStatementUpdate.executeUpdate();
            }
        }
    }

    /**
     * Сортирует записи бд по городу, а потом по дате и времени.
     *
     * @throws SQLException
     */
    public void sort() throws SQLException {
        final String sqlQuery = "SELECT * FROM " + TABLE_NAME
                + " ORDER BY city ASC, datetime ASC";
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sqlQuery);
            Map<Integer, Weather> weatherMap = new HashMap<>();
            while (rs.next()) {
                weatherMap.put(weatherMap.size() + 1,
                        new Weather(rs.getString(2),
                                rs.getTimestamp(3),
                                rs.getDouble(4),
                                rs.getDouble(5)
                        )
                );
            }
            rs.close();
            deleteAll();
            for (Integer key : weatherMap.keySet()) {
                insert(weatherMap.get(key));
            }
        }
    }

}

