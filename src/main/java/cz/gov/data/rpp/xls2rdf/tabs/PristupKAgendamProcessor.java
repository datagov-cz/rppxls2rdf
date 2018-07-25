package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Utils;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Ais;
import cz.gov.data.rpp.xls2rdf.model.PozadavekNaPristupKAgende;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import java.util.Date;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class PristupKAgendamProcessor implements TabProcessor {

    /**
     * Processes the PRISTUP K AGENDAM tab.
     *
     * @param sheet  the sheet PRISTUP K AGENDAM
     * @param agenda agenda object to be filled
     */
    public void process(final XSSFSheet sheet, final Agenda agenda) {
        Agenda agenda2 = null;
        int position = 0;
        PozadavekNaPristupKAgende pozadavek = null;
        for (final XSSFRow row : TabProcessor
            .processDynamicList(sheet, 0, "Název agendy", 1, "^$", 1)) {

            final String kodAgendy = row.getCell(0).toString();
            if (!kodAgendy.isEmpty()) {
                position = 0;
                String kod = row.getCell(0).toString();
                Date platnostOd = row.getCell(4).getDateCellValue();
                agenda2 = Registry.get(Agenda.class,Vocabulary.getClassInstance(Vocabulary.AGENDA,kod + "-" + TabProcessor.formatDate(platnostOd)));
                agenda2.setKod(kod);
                agenda2.setNazev(row.getCell(1).toString());
                agenda2.setPlatnostOd(platnostOd);
                agenda2.setPlatnostDo(row.getCell(5).toString());
                pozadavek = Registry.get(PozadavekNaPristupKAgende.class, Vocabulary.getClassInstance(Vocabulary.POZADAVEK_NA_PRISTUP_K_AGENDE, agenda.getKod() + "-" + kod));
                pozadavek.setAgendaPoskytujiciUdaje(agenda2);
                agenda.getPozadavkyNaPristupKAgendam().add(pozadavek);
            } else {
                if (position == 0) {
                    position++; // skip the header of Ais
                    continue;
                }
                position++;
                String kod = (int) row.getCell(1).getNumericCellValue() + "";
                final Ais ais = Registry.get(Ais.class, Vocabulary.getClassInstance(Vocabulary.AGENDOVY_INFORMACNI_SYSTEM, kod));
                ais.setKod(kod);
                ais.setNazev(row.getCell(2).toString());
                ais.setSpravce(row.getCell(4).toString());
                pozadavek.getAisy().add(ais);
                agenda.getPozadavkyNaPristupKAgendam().add(pozadavek);
            }
        }
    }

    public String getSheetId() {
        return TabProcessor.tabPristupKAgendam;
    }
}
