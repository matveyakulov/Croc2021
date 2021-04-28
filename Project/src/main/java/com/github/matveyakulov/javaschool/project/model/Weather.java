package com.github.matveyakulov.javaschool.project.model;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Погода.
 */
public class Weather {

    /**
     * Город.
     */
    private String city;

    /**
     * Дата и время.
     */
    private Timestamp dateTime;

    /**
     * Температура.
     */
    private double temperature;

    /**
     * Давление.
     */
    private double pressure;

    public Weather(String city, Timestamp dateTime, double temperature, double pressure) {
        this.city = city;
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.pressure = pressure;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getDateTime() {
        return dateTime;
    }

    public void setDateTime(Timestamp dateTime) {
        this.dateTime = dateTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    /**
     * Парсит String и возвращает LocalDateTime.
     *
     * @param dateTime дата и время.
     * @return обьект.
     */
    public static LocalDateTime parse(String dateTime){
        String[] dateTimeArray = dateTime.split("T");
        dateTimeArray[1].replaceAll("T", "");
        String[] dateString = dateTimeArray[0].split("-");
        String[] timeString = dateTimeArray[1].split(":");
        LocalDate date = LocalDate.of(
                Integer.parseInt(dateString[0]),
                Integer.parseInt(dateString[1]),
                Integer.parseInt(dateString[2]));
        LocalTime time = LocalTime.of(Integer.parseInt(timeString[0]),Integer.parseInt(timeString[1]));
        return LocalDateTime.of(date,time);
    }

    @Override
    public String toString() {
        return "Weather{" +
                "city='" + city + '\'' +
                ", dateTime=" + dateTime +
                ", temperature=" + temperature +
                ", pressure=" + pressure +
                '}';
    }
}
