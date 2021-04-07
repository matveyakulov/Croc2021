package com.github.matveyakulov.javaschool.homework6.model.films;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.Objects;


/**
 * Сценарист.
 */
public class ScreenWriter {

    /**
     * Имя.
     */
    @XmlElementWrapper
    @XmlAttribute
    private String name;

    public ScreenWriter() {
    }

    public ScreenWriter(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScreenWriter that = (ScreenWriter) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
