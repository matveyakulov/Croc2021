package com.github.matveyakulov.javaschool.project;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.matveyakulov.javaschool.project.database.provider.DataSourceProvider;
import com.github.matveyakulov.javaschool.project.database.service.SqlService;
import com.github.matveyakulov.javaschool.project.database.service.WeatherService;
import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlConverter;
import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlReader;
import com.github.matveyakulov.javaschool.project.model.Weather;
import com.github.matveyakulov.javaschool.project.model.WeatherPres;
import com.github.matveyakulov.javaschool.project.model.WeatherTemp;
import com.github.matveyakulov.javaschool.project.model.Weathers;
import junit.framework.TestCase;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * Демосценарий работы программы.
 */
public class DemoTest extends TestCase {

    /**
     * Демосценарий работы программы.
     */
    public void testDemo() throws IOException, SQLException {

        String xmlTemp = XmlReader.readFile("src/main/resources/Temperature.xml");
        String xmlPres = XmlReader.readFile("src/main/resources/Pressure.xml");
        ObjectMapper mapper = new ObjectMapper();
        Weathers<WeatherTemp> tempWeathers = mapper.convertValue(
                XmlConverter.fromXml(xmlTemp),
                new TypeReference<>() {
                });
        Weathers<WeatherPres> presWeathers = mapper.convertValue(
                XmlConverter.fromXml(xmlPres),
                new TypeReference<>() {
                });
        DataSourceProvider dataSource = new DataSourceProvider("app.properties");
        SqlService sqlService = new SqlService(dataSource.getDataSource());
        WeatherService weatherService = new WeatherService(sqlService);
        weatherService.deleteAll();
        for (int i = 0; i < tempWeathers.size(); i++) {
            weatherService.insert(tempWeathers.get(i));
        }
        for (int i = 0; i < presWeathers.size(); i++) {
            weatherService.insert(presWeathers.get(i));
        }
        weatherService.sort();
        // вывод на экран
        Map<Integer, Weather> weatherMap = weatherService.selectAll();
        for (Integer key : weatherMap.keySet()) {
            System.out.println(key + " " + weatherMap.get(key));
        }
    }

}
