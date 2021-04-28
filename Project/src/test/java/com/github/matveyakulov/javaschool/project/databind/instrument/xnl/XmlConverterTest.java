package com.github.matveyakulov.javaschool.project.databind.instrument.xnl;

import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlConverter;
import com.github.matveyakulov.javaschool.project.model.Weather;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherPresssure;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherTemperature;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Тесты для класса XmlConverter.
 */
public class XmlConverterTest extends TestCase {

    /**
     * Тест метода fromXml для списка Weathers<WeatherTemp>.
     *
     * @throws IOException
     */
    public void testFromXmlTemp() throws IOException {
        String xmlTempTest =
                "<weathers>\n" +
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
        Weathers<WeatherTemperature> tempWeathers = new Weathers(Arrays.asList(
                new WeatherTemperature("Krasnodar", Weather.parse("2021-02-12T20:00"), 20.5),
                new WeatherTemperature("LA", Weather.parse("2021-02-13T20:00"), 22.5)));
        Assertions.assertEquals(tempWeathers.toString(), XmlConverter.fromXml(xmlTempTest).toString());
    }

    /**
     * Тест метода fromXml для списка Weathers<WeatherPres>.
     *
     * @throws IOException
     */
    public void testFromXmlPres() throws IOException {
        String xmlPresTest =
                "<weathers>\n" +
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
        Weathers<WeatherPresssure> presWeathers = new Weathers(Arrays.asList(
                new WeatherPresssure("Krasnodar", Weather.parse("2021-02-12T20:00"), 20.5),
                new WeatherPresssure("LA", Weather.parse("2021-02-13T20:00"), 22.5)));
        Assertions.assertEquals(presWeathers.toString(), XmlConverter.fromXml(xmlPresTest).toString());

    }

}
