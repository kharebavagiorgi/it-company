package com.solvd.itcompany.main;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;

public class UniqueWordCounter {
    private static final String INPUT_FILE = "src/main/resources/book.txt";
    private static final String OUTPUT_FILE = "result.txt";
    private static final String ENCODING = "UTF-8";

    public static void main(String[] args) {
        try {

            String content = FileUtils.readFileToString(new File(INPUT_FILE), ENCODING);
            int uniqueWordCount = calculateUniqueWords(content);
            String result = "The number of unique words is: " + uniqueWordCount;

            FileUtils.writeStringToFile(new File(OUTPUT_FILE), result, ENCODING);

            System.out.println("Results written in: " + OUTPUT_FILE);
            System.out.println(result);

        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
        }
    }

    private static int calculateUniqueWords(String text) {
        String cleanedText = text.replaceAll("[^a-zA-Z\\s]", " ").toLowerCase();
        String[] words = StringUtils.split(cleanedText);

        if (words == null) {
            return 0;
        }

        Set<String> uniqueWords = Arrays.stream(words)
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toSet());

        return uniqueWords.size();
    }
}