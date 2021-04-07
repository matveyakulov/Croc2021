package com.github.matveyakulov.javaschool.homework6.model.films;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Objects;

/**
 * Режиссер.
 */
public class Director {

    /**
     * Имя.
     */
    @XmlElementWrapper
    @XmlAttribute
    private String name;

    public Director() {
    }

    public Director(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Director director = (Director) o;
        return Objects.equals(name, director.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
