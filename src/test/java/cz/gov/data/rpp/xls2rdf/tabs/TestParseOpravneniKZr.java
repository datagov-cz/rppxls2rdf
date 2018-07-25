package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.Processor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.OpravneniKUdajum;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class TestParseOpravneniKZr {

    Agenda agenda;

    @Before public void init() {
        final Processor p = new Processor();
        try {
            String fileName = "A1029_01102016.xlsx";
            agenda = p.process(fileName,getClass().getResourceAsStream("/"+fileName));;
        } catch (IOException e) {
            agenda = null;
            fail();
        }
    }

    @Test public void testParseOpravneniKZr() {
        Assert.assertEquals(39, agenda.getOpravneni().size());
        final OpravneniKUdajum opravneni =
            agenda.getOpravneni().stream().filter((v) -> v.getKod().equals("A1029-A101-1"))
                  .findAny().get();
        Assert.assertEquals(12, opravneni.getUdajeKeCteni().size());
        Assert.assertEquals(0, opravneni.getUdajeKZapisu().size());
        Assert.assertEquals(0,
            agenda.getCinnosti().stream().filter(c -> c.getKod().equals("CR6043")).findAny().get()
                  .getOpravneniKategorieAgentu().size());
        Assert.assertEquals(1,
            agenda.getCinnosti().stream().filter(c -> c.getKod().equals("CR6053")).findAny().get()
                  .getOpravneniAgenta().stream()
                  .filter(o -> o.getOpravneniKUdajum().getKod().equals("A1029-A101-1")).count());
        Assert.assertEquals(8, opravneni.getDefinujiciPredpis().size());
    }
}
