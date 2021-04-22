package com.github.matveyakulov.javaschool.project.databind.instrument.xnl;

import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlConverter;
import com.github.matveyakulov.javaschool.project.model.WeatherPres;
import com.github.matveyakulov.javaschool.project.model.WeatherTemp;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * Тесты класса XmlConverter.
 */
public class XmlConverterTest extends TestCase {

    /**
     * Тест метода fromXml при парсе Weathers<WeatherTemp>.
     *
     * @throws IOException
     */
    public void testFromXmlTemp() throws IOException {
        String xmlTempTest = "<weathers>\n" +
                "\t<weather>\n" +
                "\t\t<city>Krasnodar</city>\n" +
                "\t\t<date>2021-02-12 20:00:00.0</date>\n" +
                "\t\t<temperature>20.5</temperature>\n" +
                "\t</weather>\n" +
                "\t<weather>\n" +
                "\t\t<city>LA</city>\n" +
                "\t\t<date>2021-02-13 20:00:00.0</date>\n" +
                "\t\t<temperature>22.5</temperature>\n" +
                "\t</weather>\n" +
                "</weathers>\n";
        Weathers<WeatherTemp> tempWeathers = new Weathers(Arrays.asList(
                new WeatherTemp("Krasnodar", Timestamp.valueOf(LocalDateTime.parse("2021-02-12T20:00")), 20.5),
                new WeatherTemp("LA", Timestamp.valueOf(LocalDateTime.parse("2021-02-13T20:00")), 22.5)));
        Assertions.assertEquals(tempWeathers.toString(), XmlConverter.fromXml(xmlTempTest).toString());
    }

    /**
     * Тест метода fromXml при парсе Weathers<WeatherPres>.
     *
     * @throws IOException
     */
    public void testFromXmlPres() throws IOException {
        String xmlPresTest = "<weathers>\n" +
                "\t<weather>\n" +
                "\t\t<city>Krasnodar</city>\n" +
                "\t\t<date>2021-02-12 20:00:00.0</date>\n" +
                "\t\t<pressure>20.5</pressure>\n" +
                "\t</weather>\n" +
                "\t<weather>\n" +
                "\t\t<city>LA</city>\n" +
                "\t\t<date>2021-02-13 20:00:00.0</date>\n" +
                "\t\t<pressure>22.5</pressure>\n" +
                "\t</weather>\n" +
                "</weathers>\n";
        Weathers<WeatherPres> presWeathers = new Weathers(Arrays.asList(
                new WeatherPres("Krasnodar", Timestamp.valueOf(LocalDateTime.parse("2021-02-12T20:00")), 20.5),
                new WeatherPres("LA", Timestamp.valueOf(LocalDateTime.parse("2021-02-13T20:00")), 22.5)));
        Assertions.assertEquals(presWeathers.toString(), XmlConverter.fromXml(xmlPresTest).toString());

    }

}
