package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.KategorieOvm;
import cz.gov.data.rpp.xls2rdf.model.KategorieSpuu;
import cz.gov.data.rpp.xls2rdf.model.Ovm;
import cz.gov.data.rpp.xls2rdf.model.Spuu;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class OvmSpuuProcessor implements TabProcessor {

    public void process(final XSSFSheet sheet, final Agenda agenda) {
        processKategorieOVM(sheet, agenda);
        processOVM(sheet, agenda);
        processKategorieSPUU(sheet, agenda);
        processSPUU(sheet, agenda);
    }

    private void processSingleKategorieOVM(final XSSFRow row, final Agenda agenda) {
        final KategorieOvm typAgenta = new KategorieOvm();
        typAgenta.setIdentifikace(row.getCell(0).toString());
        typAgenta.setNazev(row.getCell(1).toString());
        typAgenta.setId(Vocabulary.getClassInstance(Vocabulary.KATEGORIE_ORGANU_VEREJNE_MOCI,typAgenta.getIdentifikace()));
        agenda.getKategorieVykonavatele().add(typAgenta);
    }

    private void processKategorieOVM(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0, "Identifikátor kategorie OVM", 1,
            "Výčet OVM, které agendu vykonávají").stream()
                    .filter(row -> !row.getCell(0).toString().isEmpty())
                    .forEach(row -> processSingleKategorieOVM(row, agenda));
    }

    private void processSingleOVM(final XSSFRow row, final Agenda agenda) {
        final Ovm agent;
        //        if (row.getCell(0).toString().equals( agenda.getOhlasovatel().getIdentifikace()
        // )) {
        //            agent = agenda.getOhlasovatel();
        //        } else {
        agent = new Ovm();
        agent.setIdentifikace(row.getCell(0).toString());
        agent.setNazev(row.getCell(1).toString());
        agent.setId(Vocabulary.getClassInstance(Vocabulary.ORGAN_VEREJNE_MOCI, agent.getIdentifikace()));
        //        }

        agenda.getVykonavatele().add(agent);
    }

    private void processOVM(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0, "Identifikátor OVM", 1,
            "Kategorie SPUU, které agendu vykonávají")
                    .forEach((row) -> processSingleOVM(row, agenda));
    }

    private void processSingleKategorieSPUU(final XSSFRow row, final Agenda agenda) {
        final KategorieSpuu typAgenta = new KategorieSpuu();
        typAgenta.setIdentifikace(row.getCell(0).toString());
        typAgenta.setNazev(row.getCell(1).toString());
        typAgenta.setId(Vocabulary.getClassInstance(Vocabulary.KATEGORIE_SOUKROMOPRAVNIHO_UZIVATELE_UDAJU, typAgenta
            .getIdentifikace()));
        agenda.getKategorieVykonavatele().add(typAgenta);
    }

    private void processKategorieSPUU(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0, "Identifikátor kategorie SPUU", 1,
            "Výčet SPUU, které agendu vykonávají").stream()
                    .filter(row -> !row.getCell(0).toString().isEmpty())
                    .forEach(row -> processSingleKategorieSPUU(row, agenda));
    }

    private void processSingleSPUU(final XSSFRow row, final Agenda agenda) {
        final Spuu agent = new Spuu();
        agent.setIdentifikace(row.getCell(0).toString());
        agent.setNazev(row.getCell(1).toString());
        agent.setId(Vocabulary.getClassInstance(Vocabulary.SOUKROMOPRAVNI_UZIVATEL_UDAJU, agent.getIdentifikace()));
        agenda.getVykonavatele().add(agent);
    }

    private void processSPUU(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0, "Identifikátor SPUU", 1, "^$").forEach((row) -> {
            processSingleSPUU(row, agenda);
        });
    }

    public String getSheetId() {
        return TabProcessor.tabDefinice;
    }
}
