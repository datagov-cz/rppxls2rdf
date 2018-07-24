package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.Processor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.KategorieOvm;
import cz.gov.data.rpp.xls2rdf.model.Ovm;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class TestParseRole {

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

    @Test public void testParseRoleProKategorieOVM() {
        Assert.assertEquals(0,
            agenda.getCinnosti().stream().filter((c) -> c.getKod().equals("CR6043")).findAny().get()
                  .getKategorieAgentuVRoli().stream().filter((ta) -> ta instanceof KategorieOvm)
                  .count());
        Assert.assertEquals(2,
            agenda.getCinnosti().stream().filter((c) -> c.getKod().equals("CR6050")).findAny().get()
                  .getKategorieAgentuVRoli().stream().filter((ta) -> ta instanceof KategorieOvm)
                  .count());
        final List<String> identifikace =
            agenda.getCinnosti().stream().filter((c) -> c.getKod().equals("CR6050")).findAny().get()
                  .getKategorieAgentuVRoli().stream().filter((ta) -> ta instanceof KategorieOvm)
                  .map((kovm) -> ((KategorieOvm) kovm).getIdentifikace())
                  .collect(Collectors.toList());
        Assert.assertEquals(2, identifikace.size());
        Assert.assertTrue(identifikace.contains("KO14"));
        Assert.assertTrue(identifikace.contains("KO137"));
    }

    @Test public void testParseRoleProOVM() {
        Assert.assertEquals(1,
            agenda.getCinnosti().stream().filter((c) -> c.getKod().equals("CR6043")).findAny().get()
                  .getAgentiVRoli().stream().filter((ta) -> ta instanceof Ovm).count());
        Assert.assertEquals(0,
            agenda.getCinnosti().stream().filter((c) -> c.getKod().equals("CR6050")).findAny().get()
                  .getAgentiVRoli().stream().filter((ta) -> ta instanceof Ovm).count());
        final List<String> identifikace =
            agenda.getCinnosti().stream().filter((c) -> c.getKod().equals("CR6043")).findAny().get()
                  .getAgentiVRoli().stream().filter((ta) -> ta instanceof Ovm)
                  .map((kovm) -> ((Ovm) kovm).getIdentifikace()).collect(Collectors.toList());
        Assert.assertEquals(1, identifikace.size());
        Assert.assertTrue(identifikace.contains("00006963"));
    }

    @Ignore @Test public void testParseRoleProSPUU() {
    }

    @Ignore @Test public void testParseRoleProKategorieSPUU() {
    }

}
