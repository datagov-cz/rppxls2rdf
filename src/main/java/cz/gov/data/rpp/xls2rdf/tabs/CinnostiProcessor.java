package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Cinnost;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import static cz.gov.data.rpp.xls2rdf.Vocabulary.DATA_BASE;

public class CinnostiProcessor implements TabProcessor {

    /**
     * Processes the CINNOSTI tab.
     *
     * @param sheet  the sheet CINNOSTI
     * @param agenda agenda object to be filled
     */
    public void process(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0,
            "Činnosti konané za účelem výkonu veřejné moci v rámci agendy", 2, "^$")
                    .forEach((row) -> {
                        final String kod = row.getCell(0).toString();
                        final Cinnost cinnost = Registry.get(
                            Cinnost.class,
                            Vocabulary.getClassInstance(Vocabulary.CINNOST,
                                kod + "-" + TabProcessor
                            .formatDate(agenda.getPlatnostOd())));
                        cinnost.setKod(row.getCell(0).toString());
                        cinnost.setNazev(row.getCell(1).toString());
                        cinnost.setPopis(row.getCell(2).toString());
                        cinnost.setPlatnostOd(row.getCell(3).toString());
                        cinnost.setPlatnostDo(row.getCell(4).toString());

                        final String value = row.getCell(5).toString();
                        if (!value.startsWith("Typ činnosti nevyplněn")) {
                            cinnost.setTypCinnosti(value);
                        }
                        agenda.getCinnosti().add(cinnost);
                    });
    }

    public String getSheetId() {
        return TabProcessor.tabCinnosti;
    }
}
