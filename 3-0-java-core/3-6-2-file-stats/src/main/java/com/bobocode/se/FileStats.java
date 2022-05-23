package com.bobocode.se;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import lombok.SneakyThrows;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    private String content;

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    @SneakyThrows
    public static FileStats from(String fileName) {
        var fileStat = new FileStats();
        URL resource = FileStats.class.getClassLoader().getResource(fileName);
        if (resource == null) {
            throw new FileStatsException("File not found");
        }
        fileStat.content = Files.readString(Path.of(resource.toURI())).replace(" ", "");
        return fileStat;
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return (int) content.codePoints()
                .mapToObj(value -> (char) value)
                .filter(c -> c == character)
                .count();


    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        var map = new HashMap<Character, Integer>();

        for (char c : content.toCharArray()) {
            map.compute(c, ((key, value) -> key == null || value == null ? 1 : value + 1));
        }

        char max = 'a';
        int count = 0;

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (count < entry.getValue()) {
                count = entry.getValue();
                max = entry.getKey();
            }
        }

        return max;
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return content.contains(String.valueOf(character));
    }
}
