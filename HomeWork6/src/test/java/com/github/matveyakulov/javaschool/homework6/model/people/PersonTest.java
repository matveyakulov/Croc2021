package com.github.matveyakulov.javaschool.homework6.model.people;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Тест класса Person.
 */
public class PersonTest extends TestCase {

    /**
     * Человек.
     */
    Person person;

    /**
     * Фильм.
     */
    FilmPerson filmPerson1;

    /**
     * Начальная сборка.
     */
    public void setUp(){
        filmPerson1 = new FilmPerson("qweas", Arrays.asList(new Function("dqw")));
        FilmPerson filmPerson2 = new FilmPerson("svfwe", Arrays.asList(new Function("wgef")));
        List<FilmPerson> filmPersonList = new ArrayList<>();
        filmPersonList.add(filmPerson1);
        filmPersonList.add(filmPerson2);
        person = new Person("qwr", filmPersonList);
    }

    /**
     * Тест метода add.
     */
    public void testAdd(){
        Assertions.assertEquals(2, person.size());
        FilmPerson filmPerson = new FilmPerson("dqwgs", Arrays.asList(new Function("dv")));
        person.addFilm(filmPerson);
        Assertions.assertEquals(3, person.size());
    }

    /**
     * Тест метода get.
     */
    public void testGet(){
        Assertions.assertEquals(filmPerson1.hashCode(), person.get("qweas").hashCode());
    }
}
