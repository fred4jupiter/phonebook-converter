package de.fred4jupiter.phonebook.converter.excel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImportService {

    public <T> List<T> importFromExcel(File file, ExcelRowMapper<T> excelRowMapper) {
        try (InputStream inp = new FileInputStream(file)) {
            return importFromInputStream(inp, excelRowMapper);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            throw new ExcelReadingException(e.getMessage(), e);
        }
    }

    public <T> List<T> importFromExcel(byte[] bytes, ExcelRowMapper<T> excelRowMapper) {
        try (InputStream inp = new ByteArrayInputStream(bytes)) {
            return importFromInputStream(inp, excelRowMapper);
        } catch (IOException | EncryptedDocumentException | InvalidFormatException e) {
            throw new ExcelReadingException(e.getMessage(), e);
        }
    }

    private <T> List<T> importFromInputStream(InputStream inp, ExcelRowMapper<T> excelRowMapper) throws IOException, InvalidFormatException {
        final List<T> resultList = new ArrayList<>();

        try (Workbook wb = WorkbookFactory.create(inp)) {
            Sheet sheet = wb.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) {
                    continue;
                }
                T mappedRow = excelRowMapper.mapRow(row);
                if (mappedRow != null) {
                    resultList.add(mappedRow);
                }
            }

            return resultList;
        }
    }
}

