package main.java.app.util;

import javafx.util.StringConverter;

public class StringConverterUtil {
    public static javafx.util.StringConverter<Integer> stringToInt(){
        return new javafx.util.StringConverter<Integer>() {
            @Override
            public String toString(Integer object) {
                return object.toString();
            }
            @Override
            public Integer fromString(String string) {
                return Integer.parseInt(string);
            }
        };
    }
    public static javafx.util.StringConverter<Float> stringToFloat(){
        return new javafx.util.StringConverter<Float>() {
            @Override
            public String toString(Float object) {
                return object.toString();
            }
            @Override
            public Float fromString(String string) {
                return Float.parseFloat(string);
            }
        };
    }
}
