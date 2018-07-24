package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Utils;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Cinnost;
import cz.gov.data.rpp.xls2rdf.model.KategorieOvm;
import cz.gov.data.rpp.xls2rdf.model.KategorieSpuu;
import cz.gov.data.rpp.xls2rdf.model.Ovm;
import cz.gov.data.rpp.xls2rdf.model.Spuu;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class RoleProcessor implements TabProcessor {

    /**
     * Processes the ROLE tab.
     *
     * @param sheet  the sheet ROLE
     * @param agenda agenda object to be filled
     */
    public void process(final XSSFSheet sheet, final Agenda agenda) {
        processKategorieOVM(sheet, agenda);
        processOVM(sheet, agenda);
        processKategorieSPUU(sheet, agenda);
        processSPUU(sheet, agenda);
    }

    private void processKategorieOVM(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicListWithDynamicColumns(sheet, 0,
            "Definice činnostních rolí pro kategorie OVM", 2,
            "Definice činnostních rolí pro " + "jednotlivě vyjmenované OVM", 2,
            "(KO[0-9]+)(\\s)*-(\\s)*(\\w.+)", (cell) -> {
                Matcher m = Pattern.compile("(KO[0-9]+).*").matcher(cell.toString());
                m.find();
                return agenda.getKategorieVykonavatele().stream()
                             .filter(c -> c instanceof KategorieOvm)
                             .filter(c -> ((KategorieOvm) c).getIdentifikace().equals(m.group(1)))
                             .findAny().get();
            }, (row, entity, colIndex) -> {
                final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                    c -> c.getKod().equals(row.getCell(0).toString())).findAny().get();
                cinnost.setKod(row.getCell(0).toString());
                if (Utils.isAno(row.getCell(colIndex))) {
                    cinnost.getKategorieAgentuVRoli().add(entity);
                }
            });
    }

    private void processOVM(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicListWithDynamicColumns(sheet, 0,
            "Definice činnostních rolí pro jednotlivě vyjmenované OVM", 2,
            "Definice činnostních " + "rolí pro kategorie SPUU", 2, "([0-9]+).(\\s)*-(\\s)*.*",
            (cell) -> {
                Matcher m = Pattern.compile("([0-9]+).*").matcher(cell.toString());
                m.find();
                return agenda.getVykonavatele().stream().filter(c -> c instanceof Ovm)
                             .filter(c -> ((Ovm) c).getIdentifikace().equals(m.group(1))).findAny()
                             .get();
            }, (row, entity, colIndex) -> {
                final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                    c -> c.getKod().equals(row.getCell(0).toString())).findAny().get();
                cinnost.setKod(row.getCell(0).toString());
                if (Utils.isAno(row.getCell(colIndex))) {
                    cinnost.getAgentiVRoli().add(entity);
                }
            });
    }

    private void processKategorieSPUU(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicListWithDynamicColumns(sheet, 0,
            "Definice činnostních rolí pro kategorie SPUU", 2,
            "Definice činnostních rolí pro " + "jednotlivě vyjmenované SPUU", 2,
            "(KS[0-9]+)(\\s)*-(\\s)*(\\w.+)", (cell) -> {
                Matcher m = Pattern.compile("(KS[0-9]+).*").matcher(cell.toString());
                m.find();
                return agenda.getKategorieVykonavatele().stream()
                             .filter(c -> c instanceof KategorieSpuu)
                             .filter(c -> ((KategorieSpuu) c).getIdentifikace().equals(m.group(1)))
                             .findAny().get();
            }, (row, entity, colIndex) -> {
                final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                    c -> c.getKod().equals(row.getCell(0).toString())).findAny().get();
                cinnost.setKod(row.getCell(0).toString());
                if (Utils.isAno(row.getCell(colIndex))) {
                    cinnost.getKategorieAgentuVRoli().add(entity);
                }
            });
    }

    private void processSPUU(final XSSFSheet sheet, final Agenda agenda) {
        TabProcessor.processDynamicListWithDynamicColumns(sheet, 0,
            "Definice činnostních rolí pro jednotlivě vyjmenované SPUU", 2, "^$", 2,
            "([0-9]+).(\\s)*-(\\s)*.*", (cell) -> {
                Matcher m = Pattern.compile("([0-9]+).*").matcher(cell.toString());
                m.find();
                return agenda.getVykonavatele().stream().filter(c -> c instanceof Spuu)
                             .filter(c -> ((Spuu) c).getIdentifikace().equals(m.group(1))).findAny()
                             .get();
            }, (row, entity, colIndex) -> {
                final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                    c -> c.getKod().equals(row.getCell(0).toString())).findAny().get();
                cinnost.setKod(row.getCell(0).toString());
                if (Utils.isAno(row.getCell(colIndex))) {
                    cinnost.getAgentiVRoli().add(entity);
                }
            });
    }

    public String getSheetId() {
        return TabProcessor.tabRole;
    }
}
