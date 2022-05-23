package com.bobocode.se;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;

import lombok.SneakyThrows;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    @SneakyThrows
    public static String readWholeFile(String fileName) {
        URL resourceAsStream = FileReaders.class.getClassLoader().getResource(fileName);
        Path path = Path.of(resourceAsStream.toURI());
        return Files.readString(path);
    }
}
