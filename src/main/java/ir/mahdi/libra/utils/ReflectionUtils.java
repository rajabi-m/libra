package ir.mahdi.libra.utils;

import java.lang.reflect.Field;
import java.time.LocalDate;

public abstract class ReflectionUtils {
    public static void setId(Object object, long id, Class<?> clazz) {
        try {
            Field idField = clazz.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(object, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setLocalDateFieldToNow(Object object, String fieldName) {
        try {
            Field dateField = object.getClass().getDeclaredField(fieldName);
            dateField.setAccessible(true);
            LocalDate now = LocalDate.now();
            dateField.set(object, now);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
