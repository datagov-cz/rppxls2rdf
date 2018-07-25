package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Utils;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Agent;
import cz.gov.data.rpp.xls2rdf.model.Cinnost;
import cz.gov.data.rpp.xls2rdf.model.KategorieOvm;
import cz.gov.data.rpp.xls2rdf.model.KategorieSpuu;
import cz.gov.data.rpp.xls2rdf.model.Objekt;
import cz.gov.data.rpp.xls2rdf.model.OpravneniAgenta;
import cz.gov.data.rpp.xls2rdf.model.OpravneniKUdajum;
import cz.gov.data.rpp.xls2rdf.model.OpravneniTypuAgenta;
import cz.gov.data.rpp.xls2rdf.model.Ovm;
import cz.gov.data.rpp.xls2rdf.model.Spuu;
import cz.gov.data.rpp.xls2rdf.model.TypAgenta;
import cz.gov.data.rpp.xls2rdf.model.Udaj;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class OpravneniKZrProcessor implements TabProcessor {

    /**
     * Processes the ROLE tab.
     *
     * @param sheet  the sheet ROLE
     * @param agenda agenda object to be filled
     */
    public void process(final XSSFSheet sheet, final Agenda agenda) {
        int row = 0;

        while (row < sheet.getPhysicalNumberOfRows()) {
            row = process(sheet, agenda, row + 1);
        }
    }

    private int process(final XSSFSheet sheet, final Agenda agenda, int fromRow) {
        int row = TabProcessor.findFirstCellInColumnByRegex(sheet, fromRow, 0, "Oprávnění: .*");
        if (row == -1) {
            return sheet.getPhysicalNumberOfRows() + 1;
        }
        final String text = sheet.getRow(row).getCell(0).toString();
        final Pattern pattern = Pattern.compile("Oprávnění: (A[0-9]+)-(A[0-9]+)-([0-9]+)");
        final Matcher matcher = pattern.matcher(text);
        matcher.find();
        final String agendaFrom = matcher.group(1);
        final String agendaTo = matcher.group(2);
        final String opravneniNr = matcher.group(3);

        String kod = agendaFrom + "-" + agendaTo + "-" + opravneniNr;
        final OpravneniKUdajum opravneni = Registry.get(OpravneniKUdajum.class, Vocabulary.getClassInstance(Vocabulary.OPRAVNENI_K_UDAJU, kod));
        opravneni.setKod(kod);
        agenda.getOpravneni().add(opravneni);

        row = row + 1;

        // READ
        List<XSSFRow> rows = TabProcessor.processDynamicList(sheet, row, "Objekt/Subjekt", 1,
            "Zapisovací oprávnění k údajům .* typ W");
        row = getRowO(opravneni, rows, row + 4);

        // WRITE
        rows = TabProcessor
            .processDynamicList(sheet, row, "Objekt/Subjekt", 1, "Oprávnění rolí kategorií OVM");
        row = getRowO(opravneni, rows, row + 4);

        AtomicInteger ii = new AtomicInteger(row);

        // KATEGORIE Ovm
        TabProcessor
            .processDynamicListWithDynamicColumns(sheet, row, "Oprávnění rolí kategorií OVM", 2,
                "Oprávnění rolí OVM", 3, "(KO[0-9]+)(\\s)*-(\\s)*(\\w.+)", (cell) -> {
                    Matcher m = Pattern.compile("(KO[0-9]+).*").matcher(cell.toString());
                    m.find();
                    return agenda.getKategorieVykonavatele().stream()
                                 .filter(c -> c instanceof KategorieOvm).filter(
                            c -> ((KategorieOvm) c).getIdentifikace().equals(m.group(1))).findAny()
                                 .get();
                }, (rowX, entity, colIndex) -> {
                    final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                        c -> c.getKod().equals(rowX.getCell(0).toString())).findAny().get();
                    setOpravneniKategorieOvm(opravneni, rowX, (KategorieOvm) entity, colIndex,
                        cinnost);
                    ii.set(rowX.getRowNum());
                });
        row = ii.get() + 1;

        //  Ovm
        TabProcessor.processDynamicListWithDynamicColumns(sheet, row, "Oprávnění rolí OVM", 2,
            "Oprávnění rolí kategorií SPUU", 3, "([0-9]+).(\\s)*-(\\s)*.*", (cell) -> {
                Matcher m = Pattern.compile("([0-9]+).*").matcher(cell.toString());
                m.find();
                return agenda.getVykonavatele().stream().filter(c -> c instanceof Ovm)
                             .filter(c -> ((Ovm) c).getIdentifikace().equals(m.group(1))).findAny()
                             .get();
            }, (rowX, entity, colIndex) -> {
                final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                    c -> c.getKod().equals(rowX.getCell(0).toString())).findAny().get();
                setOpravneniOvm(opravneni, rowX, (Ovm) entity, colIndex, cinnost);
                ii.set(rowX.getRowNum());
            });
        row = ii.get() + 1;

        // KATEGORIE SSPU
        TabProcessor
            .processDynamicListWithDynamicColumns(sheet, row, "Oprávnění rolí kategorií SPUU", 2,
                "Oprávnění rolí SPUU", 3, "(KS[0-9]+)(\\s)*-(\\s)*(\\w.+)", (cell) -> {
                    Matcher m = Pattern.compile("(KS[0-9]+).*").matcher(cell.toString());
                    m.find();
                    return agenda.getKategorieVykonavatele().stream()
                                 .filter(c -> c instanceof KategorieSpuu).filter(
                            c -> ((KategorieSpuu) c).getIdentifikace().equals(m.group(1))).findAny()
                                 .get();
                }, (rowX, entity, colIndex) -> {
                    final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                        c -> c.getKod().equals(rowX.getCell(0).toString())).findAny().get();
                    setOpravneniKategorieSsvu(opravneni, rowX, (KategorieSpuu) entity, colIndex,
                        cinnost);
                    ii.set(rowX.getRowNum());
                });
        row = ii.get() + 1;

        TabProcessor.processDynamicListWithDynamicColumns(sheet, row, "Oprávnění rolí SPUU", 2,
            "(^$)|(Definováno ustanovením právního předpisu)", 3, "([0-9]+).(\\s)*-(\\s)*.*",
            (cell) -> {
                Matcher m = Pattern.compile("([0-9]+).*").matcher(cell.toString());
                m.find();
                return agenda.getVykonavatele().stream().filter(c -> c instanceof Spuu)
                             .filter(c -> ((Spuu) c).getIdentifikace().equals(m.group(1))).findAny()
                             .get();
            }, (rowX, entity, colIndex) -> {
                final Cinnost cinnost = agenda.getCinnosti().stream().filter(
                    c -> c.getKod().equals(rowX.getCell(0).toString())).findAny().get();
                setOpravneniSpuu(opravneni, rowX, (Spuu) entity, colIndex, cinnost);
                ii.set(rowX.getRowNum());
            });
        row = ii.get() + 1;

        // READ
        rows = TabProcessor.processDynamicList(sheet, row, "Číslo", 1, "(^$)|(Oprávnění.*)");

        rows.forEach(r -> {
            opravneni.getDefinujiciPredpis()
                     .add(Utils.createPravniPredpisZeSbirkyZakonu(r, 0, 1, 2, 3, 4, 5));
            ii.set(r.getRowNum());
        });

        return row;
    }

    private void setOpravneniOvm(OpravneniKUdajum opravneni, XSSFRow rowX, Ovm entity, int colIndex,
                                 Cinnost cinnost) {
        setOpravneniAgenta(opravneni, rowX, entity, entity.getIdentifikace(), colIndex, cinnost);
    }

    private void setOpravneniSpuu(OpravneniKUdajum opravneni, XSSFRow rowX, Spuu entity,
                                  int colIndex, Cinnost cinnost) {
        setOpravneniAgenta(opravneni, rowX, entity, entity.getIdentifikace(), colIndex, cinnost);
    }

    private void setOpravneniAgenta(OpravneniKUdajum opravneni, XSSFRow rowX, Agent entity,
                                    String identifikace, int colIndex, Cinnost cinnost) {
        if (Utils.isAno(rowX.getCell(colIndex))) {
            OpravneniAgenta op = Registry.get(OpravneniAgenta.class,
                Vocabulary.getClassInstance(Vocabulary.OPRAVNENI_AGENTA,identifikace + "-" + opravneni.getKod()));
            op.setOpravneniKUdajum(opravneni);
            op.setAgent(entity);
            cinnost.getOpravneniAgenta().add(op);
        }
    }

    private void setOpravneniKategorieSsvu(OpravneniKUdajum opravneni, XSSFRow rowX,
                                           KategorieSpuu entity, int colIndex, Cinnost cinnost) {
        setOpravneniKategorieAgenta(opravneni, rowX, entity, entity.getIdentifikace(), colIndex,
            cinnost);
    }

    private void setOpravneniKategorieOvm(OpravneniKUdajum opravneni, XSSFRow rowX,
                                          KategorieOvm entity, int colIndex, Cinnost cinnost) {
        setOpravneniKategorieAgenta(opravneni, rowX, entity, entity.getIdentifikace(), colIndex,
            cinnost);
    }

    private void setOpravneniKategorieAgenta(OpravneniKUdajum opravneni, XSSFRow rowX,
                                             TypAgenta entity, String identifikace, int colIndex,
                                             Cinnost cinnost) {
        if (Utils.isAno(rowX.getCell(colIndex))) {
            OpravneniTypuAgenta op = Registry.get(OpravneniTypuAgenta.class,
                Vocabulary.getClassInstance(Vocabulary.OPRAVNENI_TYPU_AGENTA,identifikace + "-" + opravneni.getKod()));
            op.setOpravneniKUdajum(opravneni);
            op.setTypAgenta(entity);
            cinnost.getOpravneniKategorieAgentu().add(op);
        }
    }

    private int getRowO(OpravneniKUdajum opravneni, List<XSSFRow> rows, int rowNum) {
        Objekt o;
        for (XSSFRow r : rows) {
            final XSSFCell firstCell = r.getCell(0);
            if (firstCell.toString() != null && !firstCell.toString().isEmpty() && firstCell
                .toString().matches("([0-9-]+).*")) {
                final String kod = Utils.extract("([0-9-]+).*", firstCell.toString()).get(0);
                o = Registry.get(Objekt.class, Vocabulary.getClassInstance(Vocabulary.OBJEKT,kod));
                o.setKod(kod);
            } else if (r.getCell(3).toString().toString().matches("([0-9-]+).*")) {
                final String kod = Utils.extract("([0-9-]+).*", r.getCell(3).toString()).get(0);
                final Udaj udaj = Registry.get(Udaj.class, Vocabulary.getClassInstance(Vocabulary.UDAJ,kod));
                udaj.setKod(kod);
                opravneni.getUdajeKeCteni().add(udaj);
            }
            rowNum = r.getRowNum();
        }
        return rowNum;
    }

    public String getSheetId() {
        return TabProcessor.tabOpravneniKZr;
    }
}
