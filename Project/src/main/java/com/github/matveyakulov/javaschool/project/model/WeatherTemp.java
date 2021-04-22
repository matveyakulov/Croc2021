package com.github.matveyakulov.javaschool.project.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Погода с полем температуры.
 */
@XmlRootElement(name = "weather")
public class WeatherTemp {

    /**
     * Город.
     */
    @XmlElement
    private String city;

    /**
     * Дата и время.
     */
    @XmlElement(name = "datetime")
    private Timestamp dateTime;

    /**
     * Температура.
     */
    @XmlElement
    private double temperature;

    public WeatherTemp() {
    }

    public WeatherTemp(String city, Timestamp date, double temperature) {
        this.city = city;
        this.dateTime = date;
        this.temperature = temperature;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Timestamp getDatetime() {
        return dateTime;
    }

    public void setDatetime(String datetime) {
        this.dateTime = Timestamp.valueOf(LocalDateTime.parse(datetime));
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherTemp that = (WeatherTemp) o;
        return Double.compare(that.temperature, temperature) == 0 && Objects.equals(city, that.city) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, dateTime, temperature);
    }

    @Override
    public String toString() {
        return "{" +
                "city=" + city +
                ", date=" + dateTime +
                ", temperature=" + temperature +
                '}';
    }
}
