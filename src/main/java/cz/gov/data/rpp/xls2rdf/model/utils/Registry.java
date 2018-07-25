package cz.gov.data.rpp.xls2rdf.model.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang.reflect.FieldUtils;

public class Registry {

    private static final Map<String, Object> registry = new HashMap<>();

    public static void clear() {
        registry.clear();
    }

    public static <T> T get(final Class<T> c, final String id) {
        T o = (T) registry.get(id);

        if (o == null) {
            try {
                Class cc = c;
                Field f = null;
                while(f == null) {
                    try {
                        f = cc.getDeclaredField("id");
                    } catch (NoSuchFieldException e) {
                        cc = c.getSuperclass();
                    }
                }
                f.setAccessible(true);
                o = c.newInstance();
                f.set(o, id);
                registry.put(id, o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}
