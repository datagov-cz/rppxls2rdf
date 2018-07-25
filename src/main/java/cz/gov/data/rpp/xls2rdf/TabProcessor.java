package cz.gov.data.rpp.xls2rdf;

import cz.gov.data.rpp.xls2rdf.model.Agenda;
import cz.gov.data.rpp.xls2rdf.model.utils.Registry;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public interface TabProcessor {

    String tabDefinice = "DEFINICE";
    String tabOvmSpuu = "OVM-SPUU";
    String tabCinnosti = "ČINNOSTI";
    String tabRole = "ROLE";
    String tabUdaje = "ÚDAJE";
    String tabPristupKAgendam = "PŘÍSTUP K AGENDÁM";
    String tabOpravneniKZr = "OPRÁVNĚNÍ K ZR";
    String tabOpravneniKAgendam = "OPRÁVNĚNÍ K AGENDÁM";
    String tabAis = "AIS";
    String tabHistorie = "Historie";
    String tabVerze = "Verze";

    static String formatDate(final Date date) {
        return new SimpleDateFormat("yyyy.MM.dd").format(date);
    }

    static int findFirstCellInColumnByRegex(final XSSFSheet sheet, int fromRow, int column,
                                            final String regex) {
        for (int row = fromRow; row < sheet.getPhysicalNumberOfRows(); row++) {
            final XSSFRow r = sheet.getRow(row);
            String value = r.getCell(column).toString();
            if (regex == null && value == null) {
                return row;
            }

            if ((value == null ? "" : value).matches(regex)) {
                return row;
            }
        }
        return -1;
    }

    static <T> Map<Integer, T> createEntitiesFromCellsInRowByRegex(final XSSFRow row,
                                                                   int fromColumn,
                                                                   final String regex,
                                                                   final CellToEntity<T> t) {
        final Map<Integer, T> set = new HashMap<>();
        for (int column = fromColumn; column < row.getPhysicalNumberOfCells(); column++) {
            String value = row.getCell(column).toString();
            if (value == null || value.equals("")) {
                continue;
            }
            if (value.matches(regex)) {
                set.put(column, t.create(row.getCell(column)));
            } else {
                break;
            }
        }
        return set;
    }

    static List<XSSFRow> processDynamicList(final XSSFSheet sheet, int fromRow,
                                            final String startSectionRegex, int offset,
                                            final String endSectionRegex, int regexColumn) {
        int i = TabProcessor
            .findFirstCellInColumnByRegex(sheet, fromRow, regexColumn, startSectionRegex);
        int j = TabProcessor.findFirstCellInColumnByRegex(sheet, i, regexColumn, endSectionRegex);

        if (j == -1) {
            j = sheet.getPhysicalNumberOfRows();
        }

        final List<XSSFRow> rows = new ArrayList<>();
        for (int k = i + offset; k < j; k++) {
            final XSSFCell cell = sheet.getRow(k).getCell(0);
            if ((cell.toString() == null ? "" : cell.toString())
                .matches("Žádná data nejsou k dispozici")) {
                break;
            }
            rows.add(sheet.getRow(k));
        }
        return rows;
    }

    static List<XSSFRow> processDynamicList(final XSSFSheet sheet, int fromRow,
                                            final String startSectionRegex, int offset,
                                            final String endSectionRegex) {
        return processDynamicList(sheet, fromRow, startSectionRegex, offset, endSectionRegex, 0);
    }

    static <T> void processDynamicListWithDynamicColumns(final XSSFSheet sheet, int dataFromRow,
                                                         final String startSectionRegex, int offset,
                                                         final String endSectionRegex,
                                                         int headerFromCol,
                                                         final String colHeaderMatchRegex,
                                                         final CellToEntity<T>
                                                             colHeaderEntityCreator,
                                                         final CellEvaluator<T> evaluator) {
        int i = TabProcessor.findFirstCellInColumnByRegex(sheet, dataFromRow, 0, startSectionRegex);
        int j = TabProcessor.findFirstCellInColumnByRegex(sheet, i, 0, endSectionRegex);

        if (j == -1) {
            j = sheet.getPhysicalNumberOfRows();
        }

        final Map<Integer, T> entities = TabProcessor
            .createEntitiesFromCellsInRowByRegex(sheet.getRow(i + 1), headerFromCol,
                colHeaderMatchRegex, colHeaderEntityCreator);

        for (int k = i + offset; k < j; k++) {
            if (sheet.getRow(k).getCell(0).toString().matches("Žádná data nejsou k dispozici")) {
                break;
            }
            if (sheet.getRow(k).getCell(0).toString().matches("")) {
                continue;
            }
            for (final int column : entities.keySet()) {
                evaluator.eval(sheet.getRow(k), entities.get(column), column);
            }
        }
    }

    static String createHierarchicalIdentifier(String separator, String... args) {
        String id = "";

        for (final String idPart : args) {
            if (idPart == null || idPart.isEmpty()) {
                break;
            }
            id += separator + idPart;
        }
        return id;
    }

    void process(final XSSFSheet is, final Agenda agenda);

    String getSheetId();

    interface CellToEntity<T> {
        T create(XSSFCell cell);
    }

    interface CellEvaluator<T> {
        void eval(XSSFRow cell, T t, int colIndex);
    }
}
