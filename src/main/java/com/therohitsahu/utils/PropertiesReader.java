package com.therohitsahu.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    public static String readKey(String key) {

        try {
            FileInputStream fileInputStream=new FileInputStream(System.getProperty("user.dir")+"/src/main/resources/data.properties");
            Properties p = new Properties();
            p.load(fileInputStream);
            // Return empty string if key is not found
            String value = p.getProperty(key);
            return (value != null) ? value : "";  // Prevent returning null
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
