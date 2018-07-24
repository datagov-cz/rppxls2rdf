package cz.gov.data.rpp.xls2rdf;

import cz.gov.data.rpp.xls2rdf.model.Agenda;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class) public class TestProcessor {

    private String agendaFile;
    private int expectedNumberOfObjectsInAgenda;

    public TestProcessor(String agendaFile, int expectedNumberOfObjectsInAgenda) {
        this.agendaFile = agendaFile;
        this.expectedNumberOfObjectsInAgenda = expectedNumberOfObjectsInAgenda;
    }

    @Parameterized.Parameters public static Collection<Object[]> numberOfObjectsInAgenda() {
        return Arrays.asList(new Object[][] {
            //            {"A104_31012012.xlsx",4},
            //            {"A124_17072017.xlsx",2},
            {"A1029_01102016.xlsx", 2}});
    }

    public static void main(String[] args) {
        final File dir = new File(
            "/home/kremep1/fel/research/semantic-pipes/semantic-pipes-modules/module-rpp2rdf/src"
            + "/main/resources/agendas/agendas");
        final File[] directoryListing = dir.listFiles(new FilenameFilter() {
            @Override public boolean accept(File dir, String name) {
                return name.endsWith("xlsx");
            }
        });
        final Processor p = new Processor();
        if (directoryListing != null) {
            for (File child : directoryListing) {
                try {
                    p.process(new FileInputStream(child));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Test @Ignore public void testParseAgendaUdajeRpp() {
        final Processor p = new Processor();

        try {
            Agenda a = p.process(
                getClass().getResourceAsStream("/agendas/20170817-agendas/" + agendaFile));
            Assert.assertEquals(expectedNumberOfObjectsInAgenda, a.getObjekty().size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
