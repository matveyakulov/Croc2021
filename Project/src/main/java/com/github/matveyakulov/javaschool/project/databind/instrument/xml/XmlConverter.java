package com.github.matveyakulov.javaschool.project.databind.instrument.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.github.matveyakulov.javaschool.project.model.WeatherPres;
import com.github.matveyakulov.javaschool.project.model.WeatherTemp;
import com.github.matveyakulov.javaschool.project.model.Weathers;

import java.io.IOException;

/**
 * Преобразует xml.
 */
public class XmlConverter {

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

}
