package main;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileProccesing {

    static private String LINK_REGEX = "<\\s*[l|L]ink\\s*href\\s*=\\s*([^\\s>]*)";

    static List<String> findLinkedFiles(String currentPath, String fileName) throws IOException {

        List<String> result = new LinkedList<>();
        String text = UtilsOS.getFileText(currentPath + fileName);

        Pattern pattern = Pattern.compile(LINK_REGEX);
        Matcher matcher = pattern.matcher(text);
        while (matcher.find()) {
            String linkedFile = matcher.group(1);
            result.add(linkedFile.substring(1, linkedFile.length() - 1));
        }
        return result;
    }

    static Map<String, Integer> findWordsOccurences(String path){

        Map<String, Integer> dict = new HashMap<>();
        String text = null;
        try {
            text = UtilsOS.getFileText(path);
        } catch (IOException e) {
            e.printStackTrace();
            return dict;
        }
        String[] wordList = text.split("\\s");
        for (int i = 0; i < wordList.length; i++) {
            String word = wordList[i];
            dict.merge(word, 1, (a, b) -> a + b);
        }
        return dict;
    }


    static boolean replaceLineRepeats(String path) {

        File readFile = new File(path);
        if (readFile.isDirectory()) return false;
        String newFilePath = path.substring(0, path.length() - 5) + " (modified).txt";
        File writeFile = new File(newFilePath);

        BufferedReader reader;
        BufferedWriter writer;
        try {
            reader = new BufferedReader(new FileReader(readFile));
            writer = new BufferedWriter(new FileWriter(writeFile));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        String line;
        String prev_line;
        try {
            line = reader.readLine();
            while (line != null) {
                writer.write(line + "\n");
                do {
                    prev_line = line;
                    line = reader.readLine();
                } while (line != null && line.equals(prev_line));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
