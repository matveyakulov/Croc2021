package com.github.matveyakulov.javaschool.homework6.model.films;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Фильмы.
 */
@XmlRootElement(name = "films")
public class Films {

    /**
     * Список фильмов.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @XmlElement(name = "film")
    private List<Film> film;

    public Films() {
        film = new ArrayList<>();
    }

    public Films(List<Film> film) {
        this.film = film;
    }

    public List<Film> getFilm() {
        return film;
    }

    public void setFilm(List<Film> film) {
        this.film = film;
    }

    /**
     * Возвращает количество фильмов.
     *
     * @return количество фильмов.
     */
    public int size() {
        return film.size();
    }

    /**
     * Возвращает фильм по номеру.
     *
     * @param i номер фильма.
     * @return фильм.
     */
    public Film get(int i) {
        return film.get(i);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Films films1 = (Films) o;
        return Objects.equals(film, films1.film);
    }

    @Override
    public int hashCode() {
        return Objects.hash(film);
    }

}
