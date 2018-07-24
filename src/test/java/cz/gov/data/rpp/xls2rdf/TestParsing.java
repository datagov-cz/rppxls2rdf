package cz.gov.data.rpp.xls2rdf;

import cz.gov.data.rpp.xls2rdf.model.Agenda;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static junit.framework.TestCase.fail;

@RunWith(Parameterized.class) public class TestParsing {

    private final String xls;
    Agenda agenda;

    public TestParsing(String xls) {
        this.xls = xls;
    }

    @Parameterized.Parameters public static Collection<Object[]> xlsToParse() {
        return Arrays.asList(new Object[][] {{"A104_31012012.xlsx"}, {"A1029_01102016.xlsx"},
                                             {"A1185_01052015.xlsx"}, {"A1041_14062017.xlsx"}});
    }

    @Test public void init() {
        final Processor p = new Processor();
        try {
            agenda = p.process(getClass().getResourceAsStream("/" + xls));
        } catch (IOException e) {
            agenda = null;
            fail();
        }
    }
}
