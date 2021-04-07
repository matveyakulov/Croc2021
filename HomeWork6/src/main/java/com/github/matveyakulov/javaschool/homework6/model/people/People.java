package com.github.matveyakulov.javaschool.homework6.model.people;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.*;

/**
 * Люди.
 */
@XmlRootElement(name = "people")
public class People {

    /**
     * Список людей.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @XmlElement(name = "person")
    private Set<Person> persons;

    public People() {
        persons = new TreeSet<>();
    }

    public People(Set<Person> persons) {
        this.persons = persons;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        People people = (People) o;
        return Objects.equals(persons, people.persons);
    }

    @Override
    public int hashCode() {
        return Objects.hash(persons);
    }

    /**
     * Добавляет человека.
     *
     * @param person человек.
     */
    public void add(Person person) {
        persons.add(person);
    }


    /**
     * Проверяет наличие человека в списке.
     *
     * @param name имя.
     * @return true - в наличии, false - иначе.
     */
    public boolean contains(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает человека по имени.
     *
     * @param name имя.
     * @return человек.
     */
    public Person get(String name) {
        for (Person person : persons) {
            if (person.getName().equals(name)) {
                return person;
            }
        }
        return null;
    }

    /**
     * Возвращает количество человек.
     *
     * @return количество человек.
     */
    public int size() {
        return persons.size();
    }

}
