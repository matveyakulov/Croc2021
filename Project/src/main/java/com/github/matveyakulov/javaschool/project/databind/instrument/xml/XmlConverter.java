package com.github.matveyakulov.javaschool.project.databind.instrument.xml;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherPresssure;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherTemperature;

import java.io.IOException;

/**
 * Преобразует xml.
 */
public class XmlConverter{

    /**
     * Создаём настроенный mapper JAXB.
     *
     * @return mapper
     */
    private static XmlMapper createXmlMapper() {
        final XmlMapper mapper = new XmlMapper();
        mapper.registerModule(new JaxbAnnotationModule()); // модуль jaxb
        mapper.enable(SerializationFeature.INDENT_OUTPUT); // форматирование вывода
        return mapper;
    }

    /**
     * Десериализация из xml.
     *
     * @param xml xml.
     * @return объект Weathers.
     */
    public static Weathers fromXml(String xml) throws IOException {
        return createXmlMapper().readValue(xml, Weathers.class);
    }

    public static Weathers<WeatherTemperature> converterTemperature(String xml) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Weathers<WeatherTemperature> weatherT = mapper.convertValue(
                fromXml(xml),
                new TypeReference<>() {
                });
        return weatherT;
    }

    public static Weathers<WeatherPresssure> converterPressure(String xml) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Weathers<WeatherPresssure> weatherT = mapper.convertValue(
                fromXml(xml),
                new TypeReference<>() {
                });
        return weatherT;
    }


}
