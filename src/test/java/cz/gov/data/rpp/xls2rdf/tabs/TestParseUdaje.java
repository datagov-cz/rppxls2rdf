package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.Processor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Objekt;
import cz.gov.data.rpp.xls2rdf.model.PravniPredpisZeSbirkyZakonu;
import cz.gov.data.rpp.xls2rdf.model.Udaj;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class TestParseUdaje {

    Agenda agenda;

    @Before public void init() {
        final Processor p = new Processor();
        try {
            String fileName = "A104_31012012.xlsx";
            agenda = p.process(fileName,getClass().getResourceAsStream("/"+fileName));
        } catch (IOException e) {
            agenda = null;
            fail();
        }
    }

    @Test public void testParseUdaje() {
        Assert.assertEquals(4, agenda.getObjekty().size());

        final Objekt o =
            agenda.getObjekty().stream().filter(of -> of.getKod().equals("104-1")).findAny().get();
        Assert.assertEquals("Agenda a výkon agendy", o.getNazev());
        Assert.assertEquals("Agenda a výkon agendy", o.getPopis());
        Assert.assertEquals(1, o.getPravniPredpisy().size());
        Assert.assertEquals(10, o.getUdaje().size());

        final PravniPredpisZeSbirkyZakonu ppzsz =
            o.getPravniPredpisy().stream().filter(of -> of.getCislo().equals("111")).findAny()
             .get();
        Assert.assertEquals("111", ppzsz.getCislo());
        Assert.assertEquals("2009", ppzsz.getRokVydani());
        Assert.assertEquals(null, ppzsz.getPismeno());
        Assert.assertEquals("5", ppzsz.getOdstavec());
        Assert.assertEquals("51", ppzsz.getParagraf());
        Assert.assertEquals("Základní registry", ppzsz.getNazev());

        final Udaj u =
            o.getUdaje().stream().filter(of -> of.getKod().equals("104-1-1")).findAny().get();
        Assert.assertEquals("Název a číselný kód agendy", u.getNazev());
        Assert.assertEquals("Název a číselný kód agendy", u.getPopis());
        Assert.assertEquals("Referenční", u.getTyp());
        Assert.assertEquals(false, u.getVerejny());
        Assert.assertEquals(1, u.getPravniPredpisy().size());

        final PravniPredpisZeSbirkyZakonu ppzszU =
            u.getPravniPredpisy().stream().filter(of -> of.getCislo().equals("111")).findAny()
             .get();
        Assert.assertEquals("111", ppzszU.getCislo());
        Assert.assertEquals("2009", ppzszU.getRokVydani());
        Assert.assertEquals("a", ppzszU.getPismeno());
        Assert.assertEquals("5", ppzszU.getOdstavec());
        Assert.assertEquals("51", ppzszU.getParagraf());
        Assert.assertEquals("Základní registry", ppzszU.getNazev());
    }
}
