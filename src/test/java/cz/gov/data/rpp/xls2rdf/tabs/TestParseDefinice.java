package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.Processor;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.OstatniPravniPredpis;
import cz.gov.data.rpp.xls2rdf.model.PravniPredpis;
import cz.gov.data.rpp.xls2rdf.model.PravniPredpisZeSbirkyZakonu;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static junit.framework.TestCase.fail;

public class TestParseDefinice {

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

    @Test public void testParseDefiniceZakladniInformaceOOhlaseniAgendy() {
        Assert.assertEquals("A1029", agenda.getKod());
        Assert.assertEquals("Sociální zabezpečení", agenda.getNazev());
        Assert.assertEquals(new Date(116, 9, 1), agenda.getPlatnostOd());
        Assert.assertEquals("08-Feb-2018", agenda.getPlatnostDo());
        Assert.assertEquals(new Date(112, 4, 16), agenda.getDatumVzniku());
        Assert.assertEquals(null, agenda.getDatumZaniku());
    }

    @Ignore @Test public void testParseDefiniceOhlasovatelAgendy() {
        Assert.assertEquals("Ministerstvo práce a sociálních věcí",
            agenda.getOhlasovatel().getNazev());
        Assert.assertEquals("00551023", agenda.getOhlasovatel().getIdentifikace());
    }

    @Ignore @Test public void testParseDefiniceObecneInformace() {
    }

    @Test public void testParseDefinicePravniPredpisyZeSbirkyZakonu() {
        final List<PravniPredpis> s = agenda.getPravniPredpisy().stream().filter(
            (pp) -> pp instanceof PravniPredpisZeSbirkyZakonu).collect(Collectors.toList());
        Assert.assertEquals(13, s.size());
        Assert.assertEquals(3, agenda.getHlavniPravniPredpisy().stream()
                                     .filter((pp) -> pp instanceof PravniPredpisZeSbirkyZakonu)
                                     .count());
        final PravniPredpisZeSbirkyZakonu pp = (PravniPredpisZeSbirkyZakonu) s.stream().filter(
            (px) -> (((PravniPredpisZeSbirkyZakonu) px).getCislo().equals("21"))).findAny().get();
        Assert.assertEquals("21", pp.getCislo());
        Assert.assertEquals("1960", pp.getRokVydani());
        Assert.assertEquals(
            "Zákon o Úmluvě mezi Československou republikou a Maďarskou lidovou republikou o "
            + "spolupráci na poli sociální politiky",
            pp.getNazev());
        Assert.assertEquals(null, pp.getParagraf());
        Assert.assertEquals(null, pp.getOdstavec());
        Assert.assertEquals(null, pp.getPismeno());
        Assert.assertEquals(false, agenda.getHlavniPravniPredpisy().contains(pp));
    }

    @Test public void testParseDefiniceOstatniPravniPredpisy() {
        Assert.assertEquals(31,
            agenda.getPravniPredpisy().stream().filter((pp) -> pp instanceof OstatniPravniPredpis)
                  .count());
        Assert.assertEquals(0, agenda.getHlavniPravniPredpisy().stream()
                                     .filter((pp) -> pp instanceof OstatniPravniPredpis).count());
        // TODO
    }
}
