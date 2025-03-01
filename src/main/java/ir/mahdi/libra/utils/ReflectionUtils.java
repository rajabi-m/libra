package ir.mahdi.libra.utils;

import ir.mahdi.libra.model.Asset;

public abstract class ReflectionUtils {
    public static void changeId(Object object, long assetId) {
        try {
            java.lang.reflect.Field idField = Asset.class.getDeclaredField("id");
            idField.setAccessible(true);
            idField.set(object, assetId);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
}
