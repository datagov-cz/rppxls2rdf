package cz.gov.data.rpp.xls2rdf.model.utils;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class Registry {

    final Map<String, Object> registry = new HashMap<>();

    public void clear() {
        registry.clear();
    }

    public <T> T get(final Class<T> c, final String id) {
        T o = (T) registry.get(id);

        if (o == null) {
            try {
                o = c.newInstance();
                final Field f = c.getDeclaredField("id");
                f.setAccessible(true);
                f.set(o, id);
                registry.put(id, o);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            }
        }
        return o;
    }
}
