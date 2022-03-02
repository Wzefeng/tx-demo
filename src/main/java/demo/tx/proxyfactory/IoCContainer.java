package demo.tx.proxyfactory;

import java.util.HashMap;
import java.util.Map;

public class IoCContainer {

    private static final Map<Class<?>, Object> CONTAINER = new HashMap<>();

    public static <T> void put(Class<? extends T> classType, T instance) {
        CONTAINER.put(classType, instance);
    }

    public static <T> T get(Class<T> classType) {
        return (T) CONTAINER.get(classType);
    }
}
