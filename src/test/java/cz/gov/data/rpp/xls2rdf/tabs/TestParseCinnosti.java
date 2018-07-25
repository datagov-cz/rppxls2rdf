package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.Processor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Cinnost;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class TestParseCinnosti {

    Agenda agenda;

    @Before public void init() {
        final Processor p = new Processor();
        Registry.clear();
        try {
            String fileName = "A1029_01102016.xlsx";
            agenda = p.process(fileName,getClass().getResourceAsStream("/"+fileName));
        } catch (IOException e) {
            agenda = null;
            fail();
        }
    }

    @Test public void testParseCinnosti() {
        Assert.assertEquals(30, agenda.getCinnosti().size());
        final Cinnost cinnost =
            agenda.getCinnosti().stream().filter((v) -> v.getKod().equals("CR6043")).findAny()
                  .get();
        Assert.assertEquals("provádění důchodového pojištění zaměstnanců (s výjimkou příslušníků)"
                            + ", osob samostatně výdělečně činných a osob dobrovolně účastných "
                            + "důchodového pojištění", cinnost.getNazev());
        Assert.assertEquals("582/1991, § 5; 6; 8", cinnost.getPopis());
        Assert.assertEquals("01-Oct-2016", cinnost.getPlatnostOd());
        Assert.assertEquals(null, cinnost.getPlatnostDo());
        Assert.assertEquals(null, cinnost.getTypCinnosti());
    }
}
