package com.sample.utils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaoUtils {

    /**
     * fillfull new Object with sql resultSet, using reflect mechanism
     * @throws SecurityException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     * @param rs sql query result
     * @param source corresponding class with which
     * we can get all fields of Object we are going to fill
     * @throws InstantiationException
     * */
    public static Object fillObject(ResultSet rs, Class<?> source)
            throws NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException, InstantiationException {
        Field[] fs = source.getDeclaredFields();	// get all fields of the Class
        Object ts = source.getDeclaredConstructor().newInstance();		// create new object without any field initialized

        // obj[set?](rs[get?](?));	there are three varieties to set
        // 1、 field name that is not initialized
        // 2、 field type
        // 3、 field name same as 1
        for(Field f : fs) {
            String fieldName = f.getName();
            // 1、splice "set" to fieldName to make up setter "setField"
            String setFieldMethodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Class<?> returnType = f.getType();
            // 2、splice "get" to fieldType to make up "rs.getFieldType()"
            String getFieldMethodName = "get" + simplifyType(returnType.getName());
            Method setValueMethod = source.getMethod(setFieldMethodName, returnType);
            Method getValueMethod = ResultSet.class.getMethod(getFieldMethodName, String.class);
            // 3、 etc. obj.setUser_name(rs.getString("user_name"));
            setValueMethod.invoke(ts, getValueMethod.invoke(rs, humpToLine(fieldName)));
        }

        return ts;
    }


    public static String simplifyType(String type) {
        String pattern = "(\\w+)$";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(type);
        String result = null;
        while(m.find()) {
            result = m.group();
        }
        result = "Integer".equals(result) ? "int" : result;
        result = result.substring(0,1).toUpperCase() + result.substring(1);
        return result;
    }

    /**
     * change connection way of hump word
     * @param str example: myName
     * @return example: my_name
     */
    public static String humpToLine(String str) {
        return str.replaceAll("[A-Z]", "_$0").toLowerCase();
    }

}
