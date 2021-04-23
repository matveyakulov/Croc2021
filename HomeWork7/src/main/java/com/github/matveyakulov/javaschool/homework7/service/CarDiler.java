package com.github.matveyakulov.javaschool.homework7.service;

import com.github.matveyakulov.javaschool.homework7.model.Car;
import com.github.matveyakulov.javaschool.homework7.model.Controller;

import org.apache.derby.jdbc.EmbeddedDataSource;

import java.sql.*;
import java.sql.Date;
import java.util.*;

/**
 * Класс запросов к бд.
 */
public class CarDiler extends Controller {

    /**
     * Название таблицы.
     */
    private final String TABLE_NAME = "car";

    /**
     * Номер следующей строки.
     */
    private static int id = 1;

    /**
     * Data Source.
     */
    private EmbeddedDataSource dataSource;

    public CarDiler(EmbeddedDataSource dataSource) {
        this.dataSource = dataSource;
        initTable();
    }

    /**
     * Создание таблицы если ее нет.
     */
    private void initTable() {
        System.out.println(String.format("Start initializing %s table", TABLE_NAME));
        Map<Integer, Car> carList = findAll();
        Set<Integer> set = carList.keySet();
        Iterator<Integer> iter = set.iterator();
        while (iter.hasNext()) {
            id = iter.next() + 1; // получаю id
        }

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            DatabaseMetaData databaseMetadata = connection.getMetaData();
            ResultSet resultSet = databaseMetadata.getTables(
                    null,
                    null,
                    TABLE_NAME.toUpperCase(),
                    new String[]{"TABLE"});
            if (resultSet.next()) {
                System.out.println("Table has already been initialized");
            } else {
                statement.executeUpdate(
                        "CREATE TABLE "
                                + TABLE_NAME
                                + " ("
                                + "id INTEGER,"
                                + "hp INTEGER,"
                                + "number VARCHAR(10),"
                                + "wanted BOOLEAN,"
                                + "date Date,"
                                + "time Time"
                                + ")");
                System.out.println("Table was successfully initialized");
                System.setProperty("derby.language.sequence.preallocator", "1");

            }
        } catch (SQLException e) {
            System.out.println("Error occurred during table initializing: " + e.getMessage());
        } finally {
            System.out.println("=========================");
        }
    }

    @Override
    public void create(Car car) {
        final String sqlQuery = "INSERT INTO " + TABLE_NAME + " (hp, number, wanted, date, time, id)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, car.getHp());
            statement.setString(2, car.getNumber());
            statement.setBoolean(3, car.isWanted());
            statement.setDate(4, Date.valueOf(car.getDate()));
            statement.setTime(5, Time.valueOf(car.getTime()));
            statement.setInt(6, id);
            id++;
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    @Override
    public void create(int id, Car car) {
        final String sqlQuery = "INSERT INTO " + TABLE_NAME + " (id, hp, number, wanted, date, time)" +
                "VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            statement.setInt(2, car.getHp());
            statement.setString(3, car.getNumber());
            statement.setBoolean(4, car.isWanted());
            statement.setDate(5, Date.valueOf(car.getDate()));
            statement.setTime(6, Time.valueOf(car.getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    @Override
    public Car read(int id) {

        final String sqlQuery = "SELECT * FROM " + TABLE_NAME + " WHERE id = ?";
        Car car = null;
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                car = new Car(
                        resultSet.getInt("hp"),
                        resultSet.getString("number"),
                        resultSet.getBoolean("wanted"),
                        resultSet.getDate("date").toLocalDate(),
                        resultSet.getTime("time").toLocalTime());
            }


        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return car;
    }

    @Override
    public void update(int id, Car car) {
        final String sqlQuery = "UPDATE " + TABLE_NAME + " SET hp = ?, number = ?," +
                " wanted = ?, date = ?, time = ? WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            preparedStatement.setInt(1, car.getHp());
            preparedStatement.setString(2, car.getNumber());
            preparedStatement.setBoolean(3, car.isWanted());
            preparedStatement.setDate(4, Date.valueOf(car.getDate()));
            preparedStatement.setTime(5, Time.valueOf(car.getTime()));
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        final String sqlQuery = "DELETE FROM " + TABLE_NAME + " WHERE id = ?";
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sqlQuery)) {
            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }

    }

    @Override
    public Map<Integer, Car> findAll() {
        final String sqlQuery = "SELECT * FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlQuery);
            Map<Integer, Car> carMap = new HashMap();
            while (resultSet.next()) {
                carMap.put(resultSet.getInt("id"),
                        new Car(
                                resultSet.getInt("hp"),
                                resultSet.getString("number"),
                                resultSet.getBoolean("wanted"),
                                resultSet.getDate("date").toLocalDate(),
                                resultSet.getTime("time").toLocalTime())
                );
            }
            return carMap;
        } catch (Exception e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }
        return new HashMap<>();
    }

    @Override
    public void deleteAll() {
        id = 1;
        final String sqlQuery = "DELETE FROM " + TABLE_NAME;
        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sqlQuery);

        } catch (SQLException e) {
            System.out.println("Ошибка выполнения запроса: " + e.getMessage());
        }


    }
}
