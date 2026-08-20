package com.titran.oopfinalexam.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    public static Properties load(){
        var props =  new Properties();
        try (InputStream in = ConfigLoader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if(in != null){
                props.load(in);
            } else {
                throw new FileNotFoundException("property file 'config.properties' not found in the classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return props;
    }
}
