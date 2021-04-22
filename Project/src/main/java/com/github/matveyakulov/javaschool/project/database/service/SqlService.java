package com.github.matveyakulov.javaschool.project.database.service;

import com.github.matveyakulov.javaschool.project.model.Weather;
import com.github.matveyakulov.javaschool.project.model.WeatherPres;
import com.github.matveyakulov.javaschool.project.model.WeatherTemp;
import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Запросы к бд.
 */
public class SqlService {

    /**
     * Название таблицы.
     */
    private final String TABLE_NAME = "weather";

    /**
     * Data Source.
     */
    private EmbeddedDataSource dataSource;

    public SqlService(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Создание таблицы, если ее нет.
     */
    private void initTable() {
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    // Несмотря на то, что мы создаем таблицу в нижнем регистре (и дальше к ней так же обращаемся),
                    // поиск мы осуществляем в верхнем. Такие вот приколы
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
        } catch (SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.setProperty("derby.language.sequence.preallocator", "1");
        }
    }

    /**
     * Вставляет обьект WeatherTemp.
     *
     * @param weather обьект WeatherTemp.
     */
    public void insert(WeatherTemp weather) throws SQLException {

        final String sqlQuery = "INSERT INTO " + TABLE_NAME + " (city, datetime, temperature, pressure)"
                + " VALUES(?, ?, ?, 0)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, weather.getCity());
            statement.setTimestamp(2, weather.getDatetime());
            statement.setDouble(3, weather.getTemperature());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            throw e;
        }

    }

    /**
     * Вставляет обьект WeatherPres.
     *
     * @param weather обьект WeatherPres.
     */
    public void insert(WeatherPres weather) throws SQLException {

        final String sqlQuery = "INSERT INTO " + TABLE_NAME + " (city, datetime, temperature, pressure)"
                + " VALUES(?, ?, 0, ?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {

            statement.setString(1, weather.getCity());
            statement.setTimestamp(2, weather.getDatetime());
            statement.setDouble(3, weather.getPressure());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            throw e;
        }

    }

    /**
     * Вставляет обьект Weather.
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
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            throw e;
        }

    }

    /**
     * Возвращает все элементы таблицы.
     *
     * @return все элементы таблицы.
     */
    public HashMap<Integer, Weather> selectAll() throws SQLException {
        final String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            HashMap<Integer, Weather> weatherMap = new HashMap();
            while (resultSet.next()) {
                weatherMap.put(resultSet.getInt("id"),
                        new Weather(
                                resultSet.getString("city"),
                                resultSet.getTimestamp("datetime"),
                                resultSet.getDouble("temperature"),
                                resultSet.getDouble("pressure")
                        ));
            }
            return weatherMap;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Очищает и пересоздает таблицу.
     */
    public void deleteAll() throws SQLException {
        final String sqlQuery = "DROP TABLE " + TABLE_NAME; // чтобы инкремент с 1 начинался

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);
            initTable();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
            throw e;
        }


    }

    /**
     * Проверяет существование обьекта с заданными параметрами.
     *
     * @param city     город.
     * @param dateTime дата и время.
     * @return 0 - ничего не найдено, 1 - иначе.
     * @throws SQLException
     */
    public boolean exist(String city, Timestamp dateTime) throws SQLException {

        Timestamp timestampMinus = Timestamp.valueOf(dateTime.toLocalDateTime().plusHours(-1));
        Timestamp timestampPlus = Timestamp.valueOf(dateTime.toLocalDateTime().plusHours(1));
        String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE city = ?" +
                "AND (datetime BETWEEN ? AND ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setString(1, city);
            statement.setTimestamp(2, timestampMinus);
            statement.setTimestamp(3, timestampPlus);
            ResultSet rs = statement.executeQuery();

            return rs.next();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * Возвращает обьекты с указанными параметрами.
     *
     * @param city     город.
     * @param dateTime дата и время.
     * @return обьекты.
     * @throws SQLException
     */
    public Map<Integer, Weather> select(String city, Timestamp dateTime) throws SQLException {
        Timestamp timestampMinus = Timestamp.valueOf(dateTime.toLocalDateTime().plusHours(-1));
        Timestamp timestampPlus = Timestamp.valueOf(dateTime.toLocalDateTime().plusHours(1));
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
                weatherMap.put(rs.getInt("id"),
                        new Weather(rs.getString("city"),
                                rs.getTimestamp("datetime"),
                                rs.getDouble("temperature"),
                                rs.getDouble("pressure")));
            }
            return weatherMap;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

    /**
     * Обновляет температуру в записях со схожей датой.
     *
     * @param weather обьект.
     * @throws SQLException
     */
    public void update(WeatherTemp weather) throws SQLException {

        final String sqlQueryUpdate = "UPDATE " + TABLE_NAME + " SET datetime = ?, temperature = ?" +
                "WHERE id = ?";

        Map<Integer, Weather> weatherMap = select(weather.getCity(), weather.getDatetime());
        for (Integer key : weatherMap.keySet()) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlQueryUpdate)) {

                preparedStatementUpdate.setTimestamp(1, weather.getDatetime());
                preparedStatementUpdate.setDouble(2, weather.getTemperature());
                preparedStatementUpdate.setInt(3, key);
                preparedStatementUpdate.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }

    }

    /**
     * Обновляет давление в записях со схожей датой.
     *
     * @param weather обьект.
     * @throws SQLException
     */
    public void update(WeatherPres weather) throws SQLException {

        final String sqlQueryUpdate = "UPDATE " + TABLE_NAME + " SET datetime = ?, pressure = ? " +
                "WHERE id = ?";
        Map<Integer, Weather> weatherMap = select(weather.getCity(), weather.getDatetime());
        for (Integer key : weatherMap.keySet()) {
            try (Connection connection = dataSource.getConnection();
                 PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlQueryUpdate)) {

                preparedStatementUpdate.setTimestamp(1, weather.getDatetime());
                preparedStatementUpdate.setDouble(2, weather.getPressure());
                preparedStatementUpdate.setInt(3, key);
                preparedStatementUpdate.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw e;
            }
        }
    }

    /**
     * Сортировка по городу, а потом по дате и времени.
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
                        new Weather(rs.getString("city"),
                                rs.getTimestamp("datetime"),
                                rs.getDouble("temperature"),
                                rs.getDouble("pressure")
                        )
                );
            }
            deleteAll();
            for (Integer key : weatherMap.keySet()) {
                insert(weatherMap.get(key));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw e;
        }
    }

}

