package com.myMall.util;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;

/**
 * util class to read properties configuration file
 */
public class PropertiesUtil {
    private static Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
    private static Properties props;
//    private static final String FILENAME = "myMall.properties";

    static {
        final String FILENAME = "myMall.properties";
        props = new Properties();
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(FILENAME));
            props.load(in);
            readValues();
//            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(FILENAME),"UTF-8"));
        } catch (Exception e) {
            logger.error("读取配置文件出错", e);
        }
    }

    public static String getProperty(String key) {
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)) {
            return null;
        }
        return value.trim();
    }

    public static String getProperty(String key, String defaultValue) {
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)) {
            return defaultValue;
        }
        return value.trim();
    }

    public static void readValues() {
        Iterator<String> iter = props.stringPropertyNames().iterator();
        while(iter.hasNext()) {
            String key = iter.next();
            System.out.println(key + ": " + props.getProperty(key));
        }
    }
}
