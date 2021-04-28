package com.github.matveyakulov.javaschool.project.databind.instrument.xnl;

import com.github.matveyakulov.javaschool.project.databind.instrument.xml.XmlReader;
import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;

/**
 * Тест класса Reader.
 */
public class XmlReaderTest extends TestCase {

    /**
     * Тест метода readFile.
     *
     * @throws IOException
     */
    public void testReadFile() throws IOException {
        String xml = "<weathers>\n" +
                "    <weather>\n" +
                "        <city>Krasnodar</city>\n" +
                "        <date>2021-02-12T20:00</date>\n" +
                "        <pressure>20</pressure>\n" +
                "        </weather>\n" +
                "    <weather>\n" +
                "        <city>Krasnodar</city>\n" +
                "        <date>2021-02-13T20:00</date>\n" +
                "        <pressure>22</pressure>\n" +
                "    </weather>\n" +
                "</weathers>\n";
        Assertions.assertEquals(xml, XmlReader.readFile("src/test/resources/test.xml"));
    }
}
