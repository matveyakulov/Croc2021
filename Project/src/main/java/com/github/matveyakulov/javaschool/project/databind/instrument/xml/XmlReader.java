package com.github.matveyakulov.javaschool.project.databind.instrument.xml;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

        List<String> lines = Files.readAllLines(Path.of(filePath));

        StringBuilder xmlLines = new StringBuilder();

        for(String line : lines){
            xmlLines.append(line + "\n");
        }

        return xmlLines.toString();
    }


}
