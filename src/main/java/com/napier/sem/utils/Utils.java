package com.napier.sem.utils;

import com.napier.sem.Main;

import java.io.*;

/**
 * This class serves as a collection of (helper) methods that might be used at various places in the application.
 */
public class Utils {

    /**
     * Reads the specified file and returns its content as String object.
     * @param filename the name of the file to read
     * @return string containing content of the specified file
     */
    public static String getFileAsString(String filename) {
        try {
            StringBuilder resultStringBuilder = new StringBuilder();
            InputStreamReader reader = new InputStreamReader(Main.class.getResourceAsStream(filename));
            try (BufferedReader br = new BufferedReader(reader)) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultStringBuilder.append(line).append("\n");
                }
            }
            return resultStringBuilder.toString();
        } catch (IOException e) {
            return null;
        }
    }

}
