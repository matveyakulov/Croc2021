package com.github.matveyakulov.javaschool.homework6.model.people;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

/**
 * Человек
 */
public class Person implements Comparable<Person>{

    /**
     * Имя.
     */
    @XmlElement
    private String name;

    /**
     * Список фильмов.
     */
    @XmlElementWrapper(name = "films")
    @XmlElement(name = "film")
    private List<FilmPerson> films;

    public Person() {
    }

    public Person(String name, List<FilmPerson> films) {
        this.name = name;
        this.films = films;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilmPerson> getFilms() {
        return films;
    }

    public void setFilms(List<FilmPerson> films) {
        this.films = films;
    }

    /**
     * Добавляет фильм.
     *
     * @param filmPerson фильм.
     */
    public void addFilm(FilmPerson filmPerson) {
        films.add(filmPerson);
    }

    /**
     * Возвращает фильм по названию.
     *
     * @param title название.
     * @return фильм.
     */
    public FilmPerson get(String title) {
        for (int i = 0; i < films.size(); i++) {
            if (films.get(i).getTitle().equals(title)) {
                return films.get(i);
            }
        }
        return null;
    }

    /**
     * Возвращает количество элементов.
     *
     * @return количество элементов.
     */
    public int size() {
        return films.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(films, person.films);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, films);
    }

    @Override
    public int compareTo(Person other) {
        return this.name.toLowerCase().compareTo(other.getName().toLowerCase());
    }
}
