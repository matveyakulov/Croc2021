package com.github.matveyakulov.javaschool.homework7.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

/**
 * Машина.
 */
public class Car {

    /**
     * Количество лошадей.
     */
    private int hp;

    /**
     * Гос номер.
     */
    private String number;

    /**
     * В розыске( false - нет, true - да).
     */
    private boolean wanted;

    /**
     * Дата производства.
     */
    private final LocalDate date;

    /**
     * Время производства.
     */
    private final LocalTime time;

    public Car(int hp, String number, boolean wanted, String date, String time) {
        this.hp = hp;
        this.number = number;
        this.wanted = wanted;
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public Car(int hp, String number, boolean wanted, LocalDate date, LocalTime time) {
        this.hp = hp;
        this.number = number;
        this.wanted = wanted;
        this.date = date;
        this.time = time;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isWanted() {
        return wanted;
    }

    public void setWanted(boolean wanted) {
        this.wanted = wanted;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    @Override
    public String toString() {
        return "Car{" +
                "hp=" + hp +
                ", number='" + number + '\'' +
                ", wanted=" + wanted +
                ", date=" + date +
                ", time=" + time +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return hp == car.hp && wanted == car.wanted && Objects.equals(number, car.number)
                && Objects.equals(date, car.date) && Objects.equals(time, car.time);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hp, number, wanted, date, time);
    }
}
