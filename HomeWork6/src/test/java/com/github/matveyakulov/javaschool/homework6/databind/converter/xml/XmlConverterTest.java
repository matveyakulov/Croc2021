package com.github.matveyakulov.javaschool.homework6.databind.converter.xml;

import junit.framework.TestCase;
import org.junit.jupiter.api.Assertions;

import java.io.IOException;


/**
 * Тест для XmlConverter.
 */
public class XmlConverterTest extends TestCase{

    /**
     * Тест для метода convert.
     *
     * @throws IOException
     */
    public void testConvert() throws IOException {
        String xmlBefore =
                "<films>\n" +
                "    <film>\n" +
                "        <title>Фильм 1</title>\n" +
                "        <description>Описание 1</description>\n" +
                "        <screenwriters>\n" +
                "            <screenwriter name=\"Человек 1\"/>\n" +
                "            <screenwriter name=\"Человек 2\"/>\n" +
                "        </screenwriters>\n" +
                "        <directors>\n" +
                "            <director name=\"Человек 1\"/>\n" +
                "            <director name=\"Человек 3\"/>\n" +
                "        </directors>\n" +
                "    </film>\n" +
                "    <film>\n" +
                "        <title>Фильм 2</title>\n" +
                "        <description>Описание 2</description>\n" +
                "        <screenwriters>\n" +
                "            <screenwriter name=\"Человек 3\"/>\n" +
                "            <screenwriter name=\"Человек 2\"/>\n" +
                "        </screenwriters>\n" +
                "        <directors>\n" +
                "            <director name=\"Человек 2\"/>\n" +
                "            <director name=\"Человек 4\"/>\n" +
                "            <director name=\"Человек 3\"/>\n" +
                "        </directors>\n" +
                "    </film>\n" +
                "</films>";
        String xmlAfter =
                "<people>\n" +
                "   <person>\n" +
                "       <name>Человек 1</name>\n" +
                "       <films>\n" +
                "           <film title=\"Фильм 1\">\n" +
                "               <functions>\n" +
                "                   <function name=\"Сценарист\"/>\n" +
                "                   <function name=\"Режиссер\"/>\n" +
                "               </functions>\n" +
                "           </film>\n" +
                "       </films>\n" +
                "   </person>\n" +
                "   <person>\n" +
                "       <name>Человек 2</name>\n" +
                "       <films>\n" +
                "           <film title=\"Фильм 1\">\n" +
                "               <functions>\n" +
                "                   <function name=\"Сценарист\"/>\n" +
                "               </functions>\n" +
                "           </film>\n" +
                "           <film title=\"Фильм 2\">\n" +
                "               <functions>\n" +
                "                   <function name=\"Сценарист\"/>\n" +
                "                   <function name=\"Режиссер\"/>\n" +
                "               </functions>\n" +
                "           </film>\n" +
                "       </films>\n" +
                "   </person>\n" +
                "   <person>\n" +
                "       <name>Человек 3</name>\n" +
                "       <films>\n" +
                "           <film title=\"Фильм 1\">\n" +
                "               <functions>\n" +
                "                   <function name=\"Режиссер\"/>\n" +
                "               </functions>\n" +
                "           </film>\n" +
                "           <film title=\"Фильм 2\">\n" +
                "               <functions>\n" +
                "                   <function name=\"Сценарист\"/>\n" +
                "                   <function name=\"Режиссер\"/>\n" +
                "               </functions>\n" +
                "           </film>\n" +
                "       </films>\n" +
                "   </person>\n" +
                "   <person>\n" +
                "       <name>Человек 4</name>\n" +
                "       <films>\n" +
                "           <film title=\"Фильм 2\">\n" +
                "               <functions>\n" +
                "                   <function name=\"Режиссер\"/>\n" +
                "               </functions>\n" +
                "           </film>\n" +
                "       </films>\n" +
                "   </person>\n" +
                "</people>\n";

        Assertions.assertEquals(xmlAfter, XmlConverter.convert(xmlBefore));  // подаю 2 одинаковые строки,
        // но тест валится, где пропущен пробел или переход на новую строку, так и не понял
        // Чисто визуально выходит то же самое, что и в примере домашки
    }
}
