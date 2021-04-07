package com.github.matveyakulov.javaschool.homework6.model.films;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Тест класс Films.
 */
public class FilmsTest extends TestCase {

    /**
     * Список фильмов.
     */
    Films films;
    /**
     * Фильм.
     */
    Film film2;

    /**
     * Сборка.
     */
    public void setUp(){
        Film film1 = new Film("rqw", "dqwf", Arrays.asList(new ScreenWriter("qwe")),
                Arrays.asList(new Director("adw")));
        film2 = new Film("rqqwdw", "qwdsa", Arrays.asList(new ScreenWriter("vwe")),
                Arrays.asList(new Director("vw")));
        List<Film> filmList = new ArrayList<>();
        filmList.add(film1);
        filmList.add(film2);
        films = new Films(filmList);
    }

    /**
     * Тест метода size.
     */
    public void testSize(){
        Assertions.assertEquals(2, films.size());
    }

    /**
     * Тест метода get.
     */
    public void testGet(){
        Assertions.assertEquals(film2.hashCode() , films.get(1).hashCode());
    }
}
