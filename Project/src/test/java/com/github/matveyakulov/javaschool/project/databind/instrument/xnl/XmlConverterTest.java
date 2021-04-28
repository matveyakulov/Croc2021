package com.github.matveyakulov.javaschool.project.databind.instrument.xnl;

import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlConverter;
import com.github.matveyakulov.javaschool.project.model.Weather;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherPresssure;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherTemperature;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;
import java.util.Arrays;

/**
 * Тесты для класса XmlConverter.
 */
public class XmlConverterTest extends TestCase {

    /**
     * Тест метода converterTemperature.
     *
     * @throws IOException
     */
    public void testFromXmlTemp() throws IOException {
        String xmlTempTest =
                "<weathers>\n" +
                "\t<weather>\n" +
                "\t\t<city>Krasnodar</city>\n" +
                "\t\t<datetime>2021-02-12T20:00:00.0</datetime>\n" +
                "\t\t<temperature>20.5</temperature>\n" +
                "\t</weather>\n" +
                "\t<weather>\n" +
                "\t\t<city>LA</city>\n" +
                "\t\t<datetime>2021-02-13T20:00:00.0</datetime>\n" +
                "\t\t<temperature>22.5</temperature>\n" +
                "\t</weather>\n" +
                "</weathers>\n";
        Weathers<WeatherTemperature> tempWeathers = new Weathers(Arrays.asList(
                new WeatherTemperature("Krasnodar", Weather.parse("2021-02-12T20:00"), 20.5),
                new WeatherTemperature("LA", Weather.parse("2021-02-13T20:00"), 22.5)));
        Assertions.assertEquals(tempWeathers.toString(), XmlConverter.converterTemperature(xmlTempTest).toString());
    }

    /**
     * Тест метода converterPressure.
     *
     * @throws IOException
     */
    public void testFromXmlPres() throws IOException {
        String xmlPresTest =
                "<weathers>\n" +
                "\t<weather>\n" +
                "\t\t<city>Krasnodar</city>\n" +
                "\t\t<datetime>2021-02-12T20:00:00.0</datetime>\n" +
                "\t\t<pressure>20.5</pressure>\n" +
                "\t</weather>\n" +
                "\t<weather>\n" +
                "\t\t<city>LA</city>\n" +
                "\t\t<datetime>2021-02-13T20:00:00.0</datetime>\n" +
                "\t\t<pressure>22.5</pressure>\n" +
                "\t</weather>\n" +
                "</weathers>\n";
        Weathers<WeatherPresssure> presWeathers = new Weathers(Arrays.asList(
                new WeatherPresssure("Krasnodar", Weather.parse("2021-02-12T20:00"), 20.5),
                new WeatherPresssure("LA", Weather.parse("2021-02-13T20:00"), 22.5)));
        Weathers<WeatherPresssure> weathers = XmlConverter.converterPressure(xmlPresTest);
        Assertions.assertEquals(presWeathers, weathers);

    }

}
