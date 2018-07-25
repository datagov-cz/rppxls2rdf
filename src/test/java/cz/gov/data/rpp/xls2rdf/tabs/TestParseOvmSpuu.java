package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.Processor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.KategorieOvm;
import cz.gov.data.rpp.xls2rdf.model.KategorieSpuu;
import cz.gov.data.rpp.xls2rdf.model.Ovm;
import cz.gov.data.rpp.xls2rdf.model.Spuu;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class TestParseOvmSpuu {

    Agenda agenda;

    @Before public void init() {
        final Processor p = new Processor();
        try {
            String fileName = "A1029_01102016.xlsx";
            agenda = p.process(fileName,getClass().getResourceAsStream("/"+fileName));
        } catch (IOException e) {
            agenda = null;
            fail();
        }
    }

    @Test public void testParseOvmSpuuOvm() {
        Assert.assertEquals(9,
            agenda.getVykonavatele().stream().filter((v) -> v instanceof Ovm).count());
        final Ovm ovm = (Ovm) agenda.getVykonavatele().stream().filter((v) -> v instanceof Ovm)
                                    .filter((v) -> ((Ovm) v).getIdentifikace().equals("00006963"))
                                    .findAny().get();
        Assert.assertEquals("Česká správa sociálního zabezpečení", ovm.getNazev());
    }

    @Test public void testParseOvmSpuuKategorieOvm() {
        Assert.assertEquals(4,
            agenda.getKategorieVykonavatele().stream().filter((v) -> v instanceof KategorieOvm)
                  .count());
        final KategorieOvm kategorie = (KategorieOvm) agenda.getKategorieVykonavatele().stream()
                                                            .filter(
                                                                (v) -> v instanceof KategorieOvm)
                                                            .filter((v) -> ((KategorieOvm) v)
                                                                .getIdentifikace().equals("KO14"))
                                                            .findAny().get();
        Assert.assertEquals("Obce", kategorie.getNazev());

    }

    @Test public void testParseOvmSpuuSpuu() {
        Assert.assertEquals(0,
            agenda.getVykonavatele().stream().filter((v) -> v instanceof Spuu).count());
    }

    @Test public void testParseOvmSpuuKategorieSpuu() {
        Assert.assertEquals(0,
            agenda.getKategorieVykonavatele().stream().filter((v) -> v instanceof KategorieSpuu)
                  .count());
    }
}
