package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Utils;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Ovm;
import cz.gov.data.rpp.xls2rdf.model.PravniPredpis;
import java.util.stream.Collectors;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class DefiniceProcessor implements TabProcessor {

    /**
     * Processes the DEFINICE tab.
     *
     * @param sheet  DEFINICE sheet
     * @param agenda agenda object to be filled
     */
    public void process(final XSSFSheet sheet, final Agenda agenda) {
        agenda.setKod(sheet.getRow(2).getCell(3).toString());
        agenda.setNazev(sheet.getRow(3).getCell(3).toString());
        agenda.setPlatnostOd(sheet.getRow(4).getCell(3).getDateCellValue());
        agenda.setPlatnostDo(sheet.getRow(5).getCell(3).toString());
        agenda.setDatumVzniku(sheet.getRow(6).getCell(3).getDateCellValue());
        agenda.setDatumZaniku(sheet.getRow(7).getCell(3).toString());

        agenda.setId(Utils.getIdForAgenda(agenda));

        // cycles while JOPA persisting
        //        processOhlasovatelAgendy(sheet, agenda);

        processObecneInformace(sheet, agenda);

        processPravniPredpisZeSbirkyZakonu(sheet, agenda);

        processOstatniPravniPredpis(sheet, agenda);
    }

    private void processOhlasovatelAgendy(final XSSFSheet sheet, final Agenda agenda) {
        final Ovm ohlasovatel = new Ovm();
        ohlasovatel.setIdentifikace(sheet.getRow(9).getCell(3).toString());
        ohlasovatel.setNazev(sheet.getRow(10).getCell(3).toString());
        ohlasovatel.setId(Vocabulary.getClassInstance(Vocabulary.ORGAN_VEREJNE_MOCI,ohlasovatel.getIdentifikace()));
        agenda.setOhlasovatel(ohlasovatel);
    }

    private void processObecneInformace(final XSSFSheet sheet, final Agenda agenda) {
    }

    private PravniPredpis processPravniPredpisZeSbirkyZakonu(final XSSFRow row,
                                                             final Agenda agenda) {
        final PravniPredpis pravniPredpis =
            Utils.createPravniPredpisZeSbirkyZakonu(row, 0, 1, 2, 4, 5, 6);
        if (row.getCell(7).toString().equals("Ano")) {
            agenda.getHlavniPravniPredpisy().add(pravniPredpis);
        }
        return pravniPredpis;
    }

    private PravniPredpis processOstatniPravniPredpis(final XSSFRow row, final Agenda agenda) {
        final PravniPredpis pravniPredpis = Utils.createOstatniPravniPredpis(row, 0);
        if (Boolean.parseBoolean(row.getCell(7).toString())) {
            agenda.getHlavniPravniPredpisy().add(pravniPredpis);
        }
        return pravniPredpis;
    }

    private void processPravniPredpisZeSbirkyZakonu(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0,
            "Právní předpisy, na jejichž základě je agenda vykonávána – Právní předpisy ze Sbírky"
            + " zákonů", 2,
            "Právní předpisy, na jejichž základě je agenda vykonávána – Ostatní právní předpisy a"
            + " jejich ustanovení").forEach((row) -> {
            final PravniPredpis ppzz = processPravniPredpisZeSbirkyZakonu(row, agenda);
            if (agenda.getPravniPredpisy().stream().map(p -> p.getId()).collect(
                // kvuli 257	2012 v A1126
                // OPRAVA 472/2017 misto 257/2012 - pro Vyhláška o předcházení emisím
                // látek, které poškozují ozonovou vrstvu, a fluorovaných
                // skleníkových plynů
                Collectors.toList()).contains(ppzz.getId())) {
                System.out.println("ERROR - duplicate PravniPredpis with ID " + ppzz.getId()
                                   + ", skipping the new one");
            } else {
                agenda.getPravniPredpisy().add(ppzz);
            }
        });
    }

    public void processOstatniPravniPredpis(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0,
            "Právní předpisy, na jejichž základě je agenda vykonávána – Ostatní právní předpisy a"
            + " jejich ustanovení", 2, "^$").forEach(
            (row) -> agenda.getPravniPredpisy().add(processOstatniPravniPredpis(row, agenda)));
    }

    public String getSheetId() {
        return TabProcessor.tabDefinice;
    }
}
