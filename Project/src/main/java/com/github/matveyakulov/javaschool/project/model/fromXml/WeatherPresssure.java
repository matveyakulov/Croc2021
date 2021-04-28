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
public class WeatherPresssure {

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
     * Давление воздуха.
     */
    @XmlElement
    private double pressure;

    private WeatherPresssure() {
    }

    public WeatherPresssure(String city, LocalDateTime dateTime, double pressure) {
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

    public LocalDateTime getDatetime() {
        return dateTime;
    }

    public void setDatetime(String dateTime) {

        this.dateTime = Weather.parse(dateTime);
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
        WeatherPresssure that = (WeatherPresssure) o;
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
                ", date=" + Timestamp.valueOf(dateTime) +
                ", pressure=" + pressure +
                '}';
    }
}
