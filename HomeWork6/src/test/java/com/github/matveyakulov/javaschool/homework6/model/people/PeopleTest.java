package com.github.matveyakulov.javaschool.homework6.model.people;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.util.*;

/**
 * Тесты для People.
 */
public class PeopleTest extends TestCase {


    /**
     * Люди.
     */
    People people;

    /**
     * Человек.
     */
    Person person1;

    /**
     * Начальная сборка.
     */
    public void setUp() {
        Function function1 = new Function("qw");
        Function function2 = new Function("qw");
        List<Function> functions = new ArrayList<>();
        functions.add(function1);
        functions.add(function2);

        FilmPerson filmPerson1 = new FilmPerson("qwe", functions);
        FilmPerson filmPerson2 = new FilmPerson("dqw", functions);
        List<FilmPerson> filmPersonList = new ArrayList<>();
        filmPersonList.add(filmPerson1);
        filmPersonList.add(filmPerson2);

        person1 = new Person("qwe", filmPersonList);
        Person person2 = new Person("afs", filmPersonList);
        Set<Person> personList = new HashSet<>();
        personList.add(person1);
        personList.add(person2);

        people = new People(personList);
    }

    /**
     * Тест метода add.
     */
    public void testAdd() {
        Assertions.assertEquals(2, people.size());
        Person person = new Person("qwe", Arrays.asList(
                new FilmPerson("qwe", Arrays.asList(new Function("qwfadg")))));
        people.add(person);
        Assertions.assertEquals(3, people.size());
    }

    /**
     * Тест метода contains.
     */
    public void testContains() {
        Assertions.assertEquals(true, people.contains("qwe"));
    }

    /**
     * Тест метода get.
     */
    public void testGet(){
        Assertions.assertEquals(person1.hashCode(), people.get("qwe").hashCode());
    }


}
