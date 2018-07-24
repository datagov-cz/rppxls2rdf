package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.Processor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.PozadavekNaPristupKAgende;
import java.io.IOException;
import java.util.Collections;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class TestParsePozadavekNaPristupKAgende {

    Agenda agenda;

    @Before public void init() {
        final Processor p = new Processor();
        try {
            agenda = p.process(getClass().getResourceAsStream("/A1029_01102016.xlsx"));
        } catch (IOException e) {
            agenda = null;
            fail();
        }
    }

    @Test public void testParsePozadavkyNaPristupKAgende() {
        Assert.assertEquals(4, agenda.getPozadavkyNaPristupKAgendam().size());
        final PozadavekNaPristupKAgende pozadavek = agenda.getPozadavkyNaPristupKAgendam().stream()
                                                          .filter(
                                                              (v) -> v.getAgendaPoskytujiciUdaje()
                                                                      .getKod().equals("A101"))
                                                          .findAny().get();
        Assert.assertEquals("Základní registr - registr obyvatel",
            pozadavek.getAgendaPoskytujiciUdaje().getNazev());
        Assert.assertEquals(true,
            pozadavek.getAisy().stream().map(a -> a.getKod()).collect(Collectors.toList())
                     .equals(Collections.singletonList("999991")));
    }
}
