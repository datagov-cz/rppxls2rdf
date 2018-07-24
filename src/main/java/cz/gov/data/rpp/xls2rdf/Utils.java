package cz.gov.data.rpp.xls2rdf;

import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.OstatniPravniPredpis;
import cz.gov.data.rpp.xls2rdf.model.PravniPredpisZeSbirkyZakonu;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;

public class Utils {

    public static String getIdForAgenda(final Agenda agenda) {
        return Vocabulary.getClassInstance(Vocabulary.AGENDA,agenda.getKod() + "-" + TabProcessor
            .formatDate(agenda.getPlatnostOd()));
    }

    public static OstatniPravniPredpis createOstatniPravniPredpis(final XSSFRow row,
                                                                  final int iNazev) {
        return createOstatniPravniPredpis(row.getCell(iNazev).toString());
    }

    public static OstatniPravniPredpis createOstatniPravniPredpis(final String nazev) {
        OstatniPravniPredpis result = new OstatniPravniPredpis();
        result.setNazev(nazev);
        result.setId(Vocabulary.getClassInstance(Vocabulary.OSTATNI_PRAVNI_PREDPIS,urify(result.getNazev())));
        return result;
    }

    public static String urify(String s) {
        return s.toLowerCase().replaceAll("[\\s\\u00A0/\\(\\)\",]", "-");
    }

    public static List<String> extract(String regex, String text) {
        final Pattern pattern2 = Pattern.compile(regex);
        final Matcher matcher2 = pattern2.matcher(text);
        matcher2.find();
        final List<String> result = new ArrayList<>();
        for (int i = 1; i < matcher2.groupCount() + 1; i++) {
            result.add(matcher2.group(i));
        }
        return result;
    }

    public static PravniPredpisZeSbirkyZakonu createPravniPredpisZeSbirkyZakonu(final XSSFRow row,
                                                                                final int iCislo,
                                                                                final int
                                                                                    iRokVydani,
                                                                                final int iNazev,
                                                                                final int iParagraf,
                                                                                final int iOdstavec,
                                                                                final int
                                                                                    iPismeno) {
        return createPravniPredpisZeSbirkyZakonu(row.getCell(iCislo).toString(),
            row.getCell(iRokVydani).getNumericCellValue(), row.getCell(iNazev).toString(),
            row.getCell(iParagraf).toString(), row.getCell(iOdstavec).toString(),
            row.getCell(iPismeno).toString());
    }

    public static PravniPredpisZeSbirkyZakonu createPravniPredpisZeSbirkyZakonu(
        final String cisloDirty, final double rokVydani, final String nazev, final String paragraf,
        final String odstavec, final String pismeno) {
        PravniPredpisZeSbirkyZakonu pravniPredpis = new PravniPredpisZeSbirkyZakonu();
        if (cisloDirty != null) {
            pravniPredpis.setCislo(cisloDirty.replaceAll("\\s", "-"));
        }
        pravniPredpis.setRokVydani(((int) rokVydani) + "");
        pravniPredpis.setNazev(nazev);
        if (paragraf != null) {
            pravniPredpis.setParagraf(paragraf.replaceAll("\\s", "-"));
        }
        if (odstavec != null) {
            pravniPredpis.setOdstavec(odstavec.replaceAll("\\s", "-"));
        }
        if (pismeno != null) {
            pravniPredpis.setPismeno(pismeno.replaceAll("\\s", "-"));
        }
        pravniPredpis.setId(
            Vocabulary.getClassInstance(Vocabulary.PRAVNI_PREDPIS_ZE_SBIRKY_ZAKONU,
                pravniPredpis.getRokVydani() + "-" + pravniPredpis
                .getCislo() + TabProcessor
                .createHierarchicalIdentifier("-", pravniPredpis.getParagraf(),
                    pravniPredpis.getOdstavec(), pravniPredpis.getPismeno())));

        return pravniPredpis;
    }

    public static boolean isAno(final XSSFCell cell) {
        return cell.toString().equals("Ano");
    }
}
