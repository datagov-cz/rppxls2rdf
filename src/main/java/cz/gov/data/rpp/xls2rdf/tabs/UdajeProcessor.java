package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Utils;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Objekt;
import cz.gov.data.rpp.xls2rdf.model.Udaj;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import java.util.HashSet;
import java.util.Set;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class UdajeProcessor implements TabProcessor {

    public void process(final XSSFSheet sheet, final Agenda agenda) {
        final Set<Objekt> objekty = new HashSet<>();

        int i = 0;
        while (
            (i = TabProcessor.findFirstCellInColumnByRegex(sheet, i + 1, 0, "Kód objektu/subjektu"))
            > 0) {
            int iObjekt = ++i;
            final Objekt objekt = processObjekt(sheet.getRow(iObjekt));

            if (objekt == null) {
                continue;
            }
            objekty.add(objekt);

            i += 3;

            for (int j = i, k = TabProcessor.findFirstCellInColumnByRegex(sheet, j, 2, "[0-9]+");
                 k == j;
                 j += 1, k = TabProcessor.findFirstCellInColumnByRegex(sheet, j, 2, "[0-9]+")) {
                objekt.getPravniPredpisy().add(
                    Utils.createPravniPredpisZeSbirkyZakonu(sheet.getRow(k), 2, 3, 4, 5, 6, 7));
            }

            int iUdaje = TabProcessor.findFirstCellInColumnByRegex(sheet, i + 1, 0,
                "Objekty/subjekty evidované v agendě");
            if (iUdaje == -1) {
                continue;
            }

            final Set<Udaj> udaje = new HashSet<>();
            for (int j = iUdaje + 2, k = TabProcessor
                .findFirstCellInColumnByRegex(sheet, j, 1, objekt.getKod() + "-[0-9]+"); k == j;
                 j += 4, k = TabProcessor
                     .findFirstCellInColumnByRegex(sheet, j, 1, objekt.getKod() + "-[0-9]+")) {
                final XSSFRow row = sheet.getRow(k);
                final Udaj udaj = processUdaj(row);
                udaje.add(udaj);

                for (int l = k + 3, m =
                     TabProcessor.findFirstCellInColumnByRegex(sheet, l, 2, "[0-9]+"); l == m;
                     l += 1, m = TabProcessor.findFirstCellInColumnByRegex(sheet, l, 2, "[0-9]+")) {
                    udaj.getPravniPredpisy().add(
                        Utils.createPravniPredpisZeSbirkyZakonu(sheet.getRow(l), 2, 3, 4, 5, 6, 7));
                }
            }

            objekt.setUdaje(udaje);
        }

        agenda.setObjekty(objekty);
    }

    private Udaj processUdaj(final XSSFRow row) {
        final String kod = row.getCell(1).toString();
        final Udaj udaj = Registry.get(Udaj.class,Vocabulary.getClassInstance(Vocabulary.UDAJ,kod));
        udaj.setKod(kod);
        udaj.setNazev(row.getCell(2).toString());
        udaj.setPopis(row.getCell(4).toString());
        udaj.setTyp(row.getCell(5).toString());
        udaj.setVerejny(row.getCell(7).toString().equals("Ano"));
        return udaj;
    }

    private Objekt processObjekt(final XSSFRow row) {
        final String kod = row.getCell(0).toString();

        if (!kod.equals("Žádná data nejsou k dispozici")) {
            final Objekt objekt = Registry.get(Objekt.class,Vocabulary.getClassInstance(Vocabulary.OBJEKT,kod));
            objekt.setKod(kod);
            objekt.setNazev(row.getCell(2).toString());
            objekt.setPopis(row.getCell(4).toString());
            return objekt;
        }
        return null;
    }

    public String getSheetId() {
        return TabProcessor.tabUdaje;
    }
}
