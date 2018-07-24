package cz.gov.data.rpp.xls2rdf;

import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import org.junit.Assert;
import org.junit.Test;

public class TestRegistry {

    @Test public void testNotNull() {
        Registry registry = new Registry();
        final Agenda a1 = registry.get(Agenda.class, "http://example.org/a1");
        Assert.assertNotNull(a1);
    }

    @Test public void testEquals() {
        Registry registry = new Registry();
        final Agenda a1 = registry.get(Agenda.class, "http://example.org/a1");
        final Agenda a2 = registry.get(Agenda.class, "http://example.org/a1");
        Assert.assertEquals(a1, a2);
    }
}
