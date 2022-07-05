package utils;

import java.lang.reflect.Field;

public class Yso {
    public static void setFieldValue(Object obj, String fieldName, Object value) throws
            Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }
}
