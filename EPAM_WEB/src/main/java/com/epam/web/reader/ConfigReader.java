package com.epam.web.reader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {

    private static final String resource = "/config.properties";

    public static String read(String property) {
        Properties properties = new Properties();
        try(InputStream stream = ConfigReader.class.getResourceAsStream(resource)) {
            properties.load(stream);
            return properties.getProperty(property);
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }
}
