package com.github.matveyakulov.javaschool.project.model;

import java.sql.Timestamp;

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
