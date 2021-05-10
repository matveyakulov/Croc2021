package com.github.matveyakulov.javaschool.project.model.fromXml;

import com.github.matveyakulov.javaschool.project.model.Weather;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Погода с полем температуры.
 */
@XmlRootElement(name = "weather")
public class WeatherTemperature {

    /**
     * Город.
     */
    @XmlElement
    private String city;

    /**
     * Дата и время.
     */
    @XmlElement(name = "datetime")
    private LocalDateTime dateTime;

    /**
     * Температура.
     */
    @XmlElement
    private double temperature;

    private WeatherTemperature() {
    }

    public WeatherTemperature(String city, LocalDateTime date, double temperature) {
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

    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public void setDatetime(String dateTime) {
        this.dateTime = Weather.parse(dateTime);
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
        WeatherTemperature that = (WeatherTemperature) o;
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
                ", date=" + Timestamp.valueOf(dateTime) +
                ", temperature=" + temperature +
                '}';
    }

}
