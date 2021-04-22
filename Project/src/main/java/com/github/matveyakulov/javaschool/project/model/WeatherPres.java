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
public class WeatherPres {

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
     * Давление воздуха.
     */
    @XmlElement
    private double pressure;

    private WeatherPres() {
    }

    public WeatherPres(String city, Timestamp dateTime, double pressure) {
        this.city = city;
        this.dateTime = dateTime;
        this.pressure = pressure;
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

    public void setDatetime(String dateTime) {
        this.dateTime = Timestamp.valueOf(LocalDateTime.parse(dateTime));
    }

    /**
     * Возвращает время.
     *
     * @return время.
     */

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherPres that = (WeatherPres) o;
        return Double.compare(that.pressure, pressure) == 0
                && Objects.equals(city, that.city) && Objects.equals(dateTime, that.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, dateTime, pressure);
    }

    @Override
    public String toString() {
        return "{" +
                "city=" + city +
                ", date=" + dateTime +
                ", pressure=" + pressure +
                '}';
    }
}
