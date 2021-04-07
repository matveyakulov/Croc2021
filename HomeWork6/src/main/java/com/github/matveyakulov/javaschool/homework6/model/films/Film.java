package com.github.matveyakulov.javaschool.homework6.model.films;

import javax.xml.bind.annotation.*;
import java.util.List;
import java.util.Objects;

/**
 * Фильм.
 */
@XmlType(propOrder = {"title", "description", "screenWriters", "directors"})
public class Film {

    /**
     * Название.
     */
    @XmlElement
    private String title;

    /**
     * Описание.
     */
    @XmlElement
    private String description;

    /**
     * Список сценаристов.
     */
    @XmlElementWrapper(name = "screenwriters")
    @XmlElement(name = "screenwriter")
    private List<ScreenWriter> screenWriters;

    /**
     * Список режиссеров.
     */
    @XmlElementWrapper(name = "directors")
    @XmlElement(name = "director")
    private List<Director> directors;


    public Film() {
    }

    public Film(String title, String description, List<ScreenWriter> screenWriters, List<Director> directors) {
        this.title = title;
        this.description = description;
        this.screenWriters = screenWriters;
        this.directors = directors;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<ScreenWriter> getScreenWriters() {
        return screenWriters;
    }

    public void setScreenWriters(List<ScreenWriter> screenWriters) {
        this.screenWriters = screenWriters;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public void setDirectors(List<Director> directors) {
        this.directors = directors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Film film = (Film) o;
        return Objects.equals(title, film.title) && Objects.equals(description, film.description) && Objects.equals(screenWriters, film.screenWriters) && Objects.equals(directors, film.directors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, screenWriters, directors);
    }

}
