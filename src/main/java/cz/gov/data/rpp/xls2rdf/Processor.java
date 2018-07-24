package cz.gov.data.rpp.xls2rdf;

import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.tabs.CinnostiProcessor;
import cz.gov.data.rpp.xls2rdf.tabs.DefiniceProcessor;
import cz.gov.data.rpp.xls2rdf.tabs.OpravneniKZrProcessor;
import cz.gov.data.rpp.xls2rdf.tabs.OvmSpuuProcessor;
import cz.gov.data.rpp.xls2rdf.tabs.PristupKAgendamProcessor;
import cz.gov.data.rpp.xls2rdf.tabs.RoleProcessor;
import cz.gov.data.rpp.xls2rdf.tabs.UdajeProcessor;
import java.io.IOException;
import java.io.InputStream;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Processor {

    public Agenda process(final InputStream is) throws IOException {
        // init
        XSSFWorkbook myWorkBook = new XSSFWorkbook(is);

        final Agenda a = new Agenda();

        new DefiniceProcessor().process(myWorkBook.getSheet(TabProcessor.tabDefinice), a);
        new OvmSpuuProcessor().process(myWorkBook.getSheet(TabProcessor.tabOvmSpuu), a);
        new CinnostiProcessor().process(myWorkBook.getSheet(TabProcessor.tabCinnosti), a);
        new UdajeProcessor().process(myWorkBook.getSheet(TabProcessor.tabUdaje), a);
        new PristupKAgendamProcessor()
            .process(myWorkBook.getSheet(TabProcessor.tabPristupKAgendam), a);
        new OpravneniKZrProcessor().process(myWorkBook.getSheet(TabProcessor.tabOpravneniKZr), a);
        new RoleProcessor().process(myWorkBook.getSheet(TabProcessor.tabRole), a);

        return a;
    }
}
