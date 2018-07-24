package cz.gov.data.rpp.xls2rdf.tabs;

import cz.gov.data.rpp.xls2rdf.TabProcessor;
import cz.gov.data.rpp.xls2rdf.Utils;
import cz.gov.data.rpp.xls2rdf.Vocabulary;
import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.Ais;
import cz.gov.data.rpp.xls2rdf.model.PozadavekNaPristupKAgende;
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
                agenda2 = new Agenda();
                agenda2.setKod(row.getCell(0).toString());
                agenda2.setNazev(row.getCell(1).toString());
                agenda2.setPlatnostOd(row.getCell(4).getDateCellValue());
                agenda2.setPlatnostDo(row.getCell(5).toString());
                agenda2.setId(Utils.getIdForAgenda(agenda2));
                pozadavek = new PozadavekNaPristupKAgende();
                pozadavek.setAgendaPoskytujiciUdaje(agenda2);
                pozadavek.setId(Vocabulary.getClassInstance(Vocabulary.POZADAVEK_NA_PRISTUP_K_AGENDE, agenda.getKod() + "-"
                    + agenda2.getKod()));
                agenda.getPozadavkyNaPristupKAgendam().add(pozadavek);
            } else {
                if (position == 0) {
                    position++; // skip the header of Ais
                    continue;
                }
                position++;

                final Ais ais = new Ais();
                ais.setKod((int) row.getCell(1).getNumericCellValue() + "");
                ais.setNazev(row.getCell(2).toString());
                ais.setSpravce(row.getCell(4).toString());
                ais.setId(Vocabulary.getClassInstance(Vocabulary.AGENDOVY_INFORMACNI_SYSTEM,ais.getKod()));
                pozadavek.getAisy().add(ais);
                agenda.getPozadavkyNaPristupKAgendam().add(pozadavek);
            }
        }
    }

    public String getSheetId() {
        return TabProcessor.tabPristupKAgendam;
    }
}
