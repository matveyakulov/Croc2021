package com.github.matveyakulov.javaschool.project.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Мгожество погод.
 *
 * @param <T> тип погоды.
 */
@XmlRootElement(name = "weathers")
public class Weathers<T> {

    /**
     * Список погод.
     */
    @JacksonXmlElementWrapper(useWrapping = false)
    @XmlElement(name = "weather")
    private List<T> weathers;

    public Weathers() {
        weathers = new ArrayList<>();
    }

    public Weathers(List<T> weathers) {
        this.weathers = weathers;
    }

    public List<T> getWeathers() {
        return weathers;
    }

    public void setWeathers(List<T> weathers) {
        this.weathers = weathers;
    }

    @Override
    public String toString() {
        return "Weathers{" +
                "weathers=" + weathers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Weathers weathers1 = (Weathers) o;
        return Objects.equals(weathers, weathers1.weathers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weathers);
    }

    public T get(int i) {
        return weathers.get(i);
    }

    public int size() {
        return weathers.size();
    }




}
