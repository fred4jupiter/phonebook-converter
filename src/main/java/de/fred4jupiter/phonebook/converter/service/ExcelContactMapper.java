package de.fred4jupiter.phonebook.converter.service;

import de.fred4jupiter.phonebook.converter.excel.ExcelContact;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

@Component
public class ExcelContactMapper {

    public ExcelContact mapToExcelContact(Row row) {
        if (row == null || row.getCell(0) == null) {
            return null;
        }

        ExcelContact excelContact = new ExcelContact();
        excelContact.setName(convertName(row.getCell(0).getStringCellValue()));
        excelContact.setPhonePrefixHome(getNumericFieldAsString(row, 4));
        excelContact.setPhoneHome(getNumericFieldAsString(row, 5));
        excelContact.setPhonePrefixMobile(getNumericFieldAsString(row, 6));
        excelContact.setPhoneMobile(getNumericFieldAsString(row, 7));
        return excelContact;
    }

    private String convertName(String name) {
        if (StringUtils.isBlank(name)) {
            return "unknown";
        }

        if (name.contains(",")) {
            String[] splitted = StringUtils.split(name, ",");
            if (splitted.length == 1) {
                return splitted[0].trim();
            }
            return splitted[1].trim() + " " + splitted[0].trim();
        }

        return name;
    }

    private String getNumericFieldAsString(Row row, int index) {
        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(row.getCell(index));
    }

}
