package com.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {

    private static InputStream input;
    private static Properties property;
    private static String path;

    static{

        String env = System.getProperty("env","qa");
        if(env.equalsIgnoreCase("qa")){
            path="config-manager/config-qa.properties";
        }
        else if(env.equalsIgnoreCase("uat")){
            path="config-manager/config-uat.properties";
        }

        input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        property = new Properties();
        try {
            property.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getValue(String value){

        return property.getProperty(value);

    }

}
