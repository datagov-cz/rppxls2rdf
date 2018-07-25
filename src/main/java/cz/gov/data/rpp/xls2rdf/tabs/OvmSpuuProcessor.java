package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.KategorieOvm;
import cz.gov.data.rpp.xls2rdf.model.KategorieSpuu;
import cz.gov.data.rpp.xls2rdf.model.Ovm;
import cz.gov.data.rpp.xls2rdf.model.Spuu;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
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
        final String identifikace = row.getCell(0).toString();
        final KategorieOvm typAgenta = Registry.get(KategorieOvm.class,Vocabulary.getClassInstance(Vocabulary.KATEGORIE_ORGANU_VEREJNE_MOCI,identifikace));
        typAgenta.setIdentifikace(identifikace);
        typAgenta.setNazev(row.getCell(1).toString());
        agenda.getKategorieVykonavatele().add(typAgenta);
    }

    private void processKategorieOVM(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0, "Identifikátor kategorie OVM", 1,
            "Výčet OVM, které agendu vykonávají").stream()
                    .filter(row -> !row.getCell(0).toString().isEmpty())
                    .forEach(row -> processSingleKategorieOVM(row, agenda));
    }

    private void processSingleOVM(final XSSFRow row, final Agenda agenda) {
        final String identifikace = row.getCell(0).toString();
        final Ovm agent = Registry.get(Ovm.class,Vocabulary.getClassInstance(Vocabulary.ORGAN_VEREJNE_MOCI,identifikace));
        agent.setIdentifikace(identifikace);
        agent.setNazev(row.getCell(1).toString());
        agenda.getVykonavatele().add(agent);
    }

    private void processOVM(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0, "Identifikátor OVM", 1,
            "Kategorie SPUU, které agendu vykonávají")
                    .forEach((row) -> processSingleOVM(row, agenda));
    }

    private void processSingleKategorieSPUU(final XSSFRow row, final Agenda agenda) {
        final String identifikace = row.getCell(0).toString();
        final KategorieSpuu typAgenta = Registry.get(KategorieSpuu.class,Vocabulary.getClassInstance(Vocabulary.KATEGORIE_SOUKROMOPRAVNIHO_UZIVATELE_UDAJU,identifikace));
        typAgenta.setIdentifikace(identifikace);
        typAgenta.setNazev(row.getCell(1).toString());
        agenda.getKategorieVykonavatele().add(typAgenta);
    }

    private void processKategorieSPUU(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicList(sheet, 0, "Identifikátor kategorie SPUU", 1,
            "Výčet SPUU, které agendu vykonávají").stream()
                    .filter(row -> !row.getCell(0).toString().isEmpty())
                    .forEach(row -> processSingleKategorieSPUU(row, agenda));
    }

    private void processSingleSPUU(final XSSFRow row, final Agenda agenda) {
        final String identifikace = row.getCell(0).toString();
        final Spuu agent = Registry.get(Spuu.class,Vocabulary.getClassInstance(Vocabulary.SOUKROMOPRAVNI_UZIVATEL_UDAJU,identifikace));
        agent.setIdentifikace(identifikace);
        agent.setNazev(row.getCell(1).toString());
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
