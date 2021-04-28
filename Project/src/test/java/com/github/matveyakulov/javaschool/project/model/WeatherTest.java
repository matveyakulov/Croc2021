package com.github.matveyakulov.javaschool.project.model;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Тест класса Weather.
 */
public class WeatherTest extends TestCase {

    /**
     * Тест метода parse.
     */
    public void testParse(){
        String dateTime = "2021-02-13T20:00";
        String[] dateTimeString = dateTime.split("T");
        dateTimeString[1].replaceAll("T", "");
        String[] dateString = dateTimeString[0].split("-");
        String[] timeString = dateTimeString[1].split(":");
        LocalDate date = LocalDate.of(
                Integer.parseInt(dateString[0]),
                Integer.parseInt(dateString[1]),
                Integer.parseInt(dateString[2]));
        LocalTime time = LocalTime.of(Integer.parseInt(timeString[0]),Integer.parseInt(timeString[1]));
        Assertions.assertEquals(LocalDate.of(2021, 02, 13), date);
        Assertions.assertEquals(LocalTime.of(20, 00), time);
    }
}
