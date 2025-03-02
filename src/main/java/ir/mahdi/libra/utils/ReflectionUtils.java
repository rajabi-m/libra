package ir.mahdi.libra.utils;

import java.lang.reflect.Field;

public abstract class ReflectionUtils {
    public static void changeId(Object object, long id) {
        try {
            Field idField = object.getClass().getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(object, id);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
