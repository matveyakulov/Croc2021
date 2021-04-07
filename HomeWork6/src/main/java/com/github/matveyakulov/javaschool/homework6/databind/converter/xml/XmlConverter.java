package com.github.matveyakulov.javaschool.homework6.databind.converter.xml;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import com.github.matveyakulov.javaschool.homework6.model.films.Director;
import com.github.matveyakulov.javaschool.homework6.model.films.Films;
import com.github.matveyakulov.javaschool.homework6.model.films.ScreenWriter;
import com.github.matveyakulov.javaschool.homework6.model.people.FilmPerson;
import com.github.matveyakulov.javaschool.homework6.model.people.Function;
import com.github.matveyakulov.javaschool.homework6.model.people.People;
import com.github.matveyakulov.javaschool.homework6.model.people.Person;

import java.io.IOException;
import java.util.*;


/**
 * XmlConverter.
 */
public class XmlConverter extends Module{

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

    @Override
    public String getModuleName() {
        return null;
    }

    @Override
    public Version version() {
        return null;
    }

    @Override
    public void setupModule(Module.SetupContext setupContext) {

    }

    /**
     * Сериализует объект в xml.
     *
     * @param data объект
     * @return xml
     */
    private static String toXml(People data) throws JsonProcessingException {
        return createXmlMapper().writeValueAsString(data);
    }

    /**
     * Десериализация из xml.
     *
     * @param xml xml.
     * @return объект Films.
     */
    private static Films fromXml(String xml) throws IOException {
        return createXmlMapper().readValue(xml, Films.class);
    }

    /**
     * Преобразование xml из одного вида в другой.
     *
     * @param xml входной.
     * @return xml преобразованный.
     * @throws IOException
     */
    public static String convert(String xml) throws IOException {
        Films films = fromXml(xml);
        People people = new People();
        for (int i = 0; i < films.size(); i++) {
            List<ScreenWriter> screenWriters = films.get(i).getScreenWriters();
            for (int j = 0; j < screenWriters.size(); j++) {
                if (people.contains(screenWriters.get(j).getName()) && // нашли человека и фильм
                        !Objects.isNull(people.get(screenWriters.get(j).getName()).get(films.get(i).getTitle()))) {
                    people.get(screenWriters.get(j).getName())
                            .get(films.get(i).getTitle())
                            .addFunc(new Function("Сценарист"));
                } else {
                    if (people.contains(screenWriters.get(j).getName()) &&  // нашли только человека
                            Objects.isNull(people.get(screenWriters.get(j).getName()).get(films.get(i).getTitle()))) {
                        people.get(screenWriters.get(j).getName())
                                .addFilm(new FilmPerson(films.get(i).getTitle(), new ArrayList<>()));
                        people.get(screenWriters.get(j).getName()).get(films.get(i).getTitle())
                                .addFunc(new Function("Сценарист"));
                    } else {  // ничего не нашли
                        Person person = new Person(screenWriters.get(j).getName(), new ArrayList<>());
                        person.addFilm(new FilmPerson(films.get(i).getTitle(), new ArrayList<>()));
                        person.get(films.get(i).getTitle()).addFunc(new Function("Сценарист"));
                        people.add(person);
                    }
                }
            }
            List<Director> directors = films.get(i).getDirectors();
            for (int j = 0; j < directors.size(); j++) {
                if (people.contains(directors.get(j).getName()) &&  // нашли человека и фильм
                        !Objects.isNull(people.get(directors.get(j).getName()).get(films.get(i).getTitle()))) {
                    people.get(directors.get(j).getName())
                            .get(films.get(i).getTitle())
                            .addFunc(new Function("Режиссер"));
                } else {
                    if (people.contains(directors.get(j).getName()) &&  // нашли только человека
                            Objects.isNull(people.get(directors.get(j).getName()).get(films.get(i).getTitle()))) {
                        people.get(directors.get(j).getName())
                                .addFilm(new FilmPerson(films.get(i).getTitle(), new ArrayList<>()));
                        people.get(directors.get(j).getName()).get(films.get(i).getTitle())
                                .addFunc(new Function("Сценарист"));
                    } else {  // ничего не нашли
                        Person person;
                        person = new Person(directors.get(j).getName(),
                                new ArrayList<>());
                        person.addFilm(new FilmPerson(films.get(i).getTitle(), new ArrayList<>()));
                        person.get(films.get(i).getTitle()).addFunc(new Function("Режиссер"));
                        people.add(person);
                    }
                }
            }
        }

        return toXml(people);
    }

}


