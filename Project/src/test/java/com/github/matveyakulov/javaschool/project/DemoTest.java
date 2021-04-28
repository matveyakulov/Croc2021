package com.github.matveyakulov.javaschool.project;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matveyakulov.javaschool.project.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlConverter;
import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlReader;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherPresssure;
import com.github.matveyakulov.javaschool.project.model.fromXml.WeatherTemperature;
import com.github.matveyakulov.javaschool.project.service.WeatherInquiries;
import com.github.matveyakulov.javaschool.project.service.WeatherService;
import junit.framework.TestCase;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Демосценарий работы программы.
 */
public class DemoTest extends TestCase {

    /**
     * Демосценарий работы программы.
     */
    public void testDemo() throws IOException {

        String xmlTemp = XmlReader.readFile("src/main/resources/Temperature.xml");
        String xmlPres = XmlReader.readFile("src/main/resources/Pressure.xml");
        ObjectMapper mapper = new ObjectMapper();
        Weathers<WeatherTemperature> tempWeathers = XmlConverter.converterTemperature(xmlTemp);
        Weathers<WeatherPresssure> presWeathers = XmlConverter.converterPressure(xmlPres);
        DataSourceProvider dataSource = new DataSourceProvider("app.properties");
        WeatherInquiries weatherInquiries;
        WeatherService weatherService;
        try {
            weatherInquiries = new WeatherInquiries(dataSource.getDataSource());
            weatherService = new WeatherService(weatherInquiries);
            weatherService.deleteAll();
            for (int i = 0; i < tempWeathers.size(); i++) {
                weatherService.insert(tempWeathers.get(i));
            }
            for (int i = 0; i < presWeathers.size(); i++) {
                weatherService.insert(presWeathers.get(i));
            }

        }catch (SQLException e){
            System.out.println("Таблица не создана, обновите данные и повторите");
            System.exit(0);
        }


    }

}
