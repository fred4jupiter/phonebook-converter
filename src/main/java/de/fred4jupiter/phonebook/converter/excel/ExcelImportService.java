package de.fred4jupiter.phonebook.converter.excel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ExcelImportService {

    public List<ExcelContact> importFromExcel(File file) {
        try (InputStream inp = new FileInputStream(file)) {
            return importFromInputStream(inp);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            throw new ExcelReadingException(e.getMessage(), e);
        }
    }

    public List<ExcelContact> importFromExcel(byte[] bytes) {
        try (InputStream inp = new ByteArrayInputStream(bytes)) {
            return importFromInputStream(inp);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            throw new ExcelReadingException(e.getMessage(), e);
        }
    }

    private List<ExcelContact> importFromInputStream(InputStream inp) throws IOException, InvalidFormatException {
        final List<ExcelContact> excelContactList = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(inp)) {
            Sheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                ExcelContact excelContact = convertRowToExcelContact(row);
                if (excelContact != null) {
                    excelContactList.add(excelContact);
                }
            }

            return excelContactList;
        }
    }

    private ExcelContact convertRowToExcelContact(Row row) {
        if (row == null || row.getCell(0) == null) {
            return null;
        }

        // TODO: 02.04.2019  map fields
        String country1 = row.getCell(0).getStringCellValue();
        String country2 = row.getCell(1).getStringCellValue();
        String group = row.getCell(2).getStringCellValue();
        Date kickOffDate = HSSFDateUtil.getJavaDate(row.getCell(3).getNumericCellValue());
        String stadium = row.getCell(4).getStringCellValue();

        ExcelContact excelContact = new ExcelContact();

        return excelContact;
    }
}

