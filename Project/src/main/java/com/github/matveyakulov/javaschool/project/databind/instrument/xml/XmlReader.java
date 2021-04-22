package com.github.matveyakulov.javaschool.project.databind.instrument.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Класс для чтения текста из файла.
 */
public class XmlReader {

    /**
     * Читает файл и возвращает строку с его содержимым.
     *
     * @param filePath путь к файлу.
     * @return строка.
     * @throws IOException
     */
    public static String readFile(String filePath) throws IOException {

        StringBuilder lines = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.append(line + "\n");
            }

        }
        return lines.toString();
    }


}
