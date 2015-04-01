package com.epam.aa.sportplace.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationProperties {
    private static final Logger logger = LoggerFactory.getLogger(ConfigurationProperties.class);

    private static final String DAO_PROPERTIES_FILE = "/dao.properties";
    private static final Properties DAO_PROPERTIES = new java.util.Properties();

    static {
        InputStream in = ConfigurationProperties.class.getResourceAsStream(DAO_PROPERTIES_FILE);
        if (in == null) {
            ConfigurationException configurationException = new ConfigurationException(
                    "ConfigurationProperties file '" + DAO_PROPERTIES_FILE + "' is missing in classpath.");
            logger.error(configurationException.getMessage(), configurationException);
            throw configurationException;
        }
        try {
            DAO_PROPERTIES.load(in);
            in.close();
        } catch (IOException e) {
            ConfigurationException configurationException = new ConfigurationException(
                    "Cannot load properties file '" + DAO_PROPERTIES_FILE + "'.", e);
            logger.error(configurationException.getMessage(), configurationException);
            throw configurationException;
        }
    }

    public static String getProperty(String key) throws ConfigurationException {
        String property = DAO_PROPERTIES.getProperty(key);
        if (property == null || property.trim().length() == 0) {
            ConfigurationException configurationException = new ConfigurationException(
                    "Required property '" + key + "'"
                            + " is missing in properties file '" + DAO_PROPERTIES_FILE + "'.");
            logger.error(configurationException.getMessage(), configurationException);
            throw configurationException;
        }
        return property;
    }
}
