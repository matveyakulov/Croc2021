package com.github.matveyakulov.javaschool.homework6.model.people;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;
import java.util.Objects;

/**
 * Фильм для Person.
 */
public class FilmPerson {

    /**
     * Название.
     */
    @XmlAttribute
    private String title;


    /**
     * Список должностей.
     */
    @XmlElementWrapper(name = "functions")
    @XmlElement(name = "function")
    private List<Function> functions;

    public FilmPerson() {
    }

    public FilmPerson(String title, List<Function> functions) {
        this.title = title;
        this.functions = functions;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Function> getFunctions() {
        return functions;
    }

    public void setFunctions(List<Function> functions) {
        this.functions = functions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FilmPerson that = (FilmPerson) o;
        return Objects.equals(title, that.title) && Objects.equals(functions, that.functions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, functions);
    }

    public void addFunc(Function function) {
        functions.add(function);
    }
}
