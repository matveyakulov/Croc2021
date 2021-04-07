package com.github.matveyakulov.javaschool.homework6.model.people;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Должности.
 */
public class Function {

    /**
     * Имя.
     */
    @XmlElementWrapper
    @XmlAttribute
    private String name;

    public Function() {
    }

    public Function(String name) {
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
        Function function = (Function) o;
        return Objects.equals(name, function.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
