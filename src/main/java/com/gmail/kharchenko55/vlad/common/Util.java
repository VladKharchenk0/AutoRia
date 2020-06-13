package com.gmail.kharchenko55.vlad.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Util {
    public static String getSearchUrl() {
        Properties prop = null;
        try {
            prop = readPropertiesFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(prop).getProperty("base.search.url");
    }

    public static String getInfoUrl() {
        Properties prop = null;
        try {
            prop = readPropertiesFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Objects.requireNonNull(prop).getProperty("base.info.url");
    }

    private static Properties readPropertiesFile() throws IOException {
        FileInputStream fis = null;
        Properties prop = null;
        try {
            fis = new FileInputStream("src/main/resources/autoria.properties");
            prop = new Properties();
            prop.load(fis);
        } catch (IOException fnfe) {
            fnfe.printStackTrace();
        } finally {
            fis.close();
        }

        return prop;
    }
}