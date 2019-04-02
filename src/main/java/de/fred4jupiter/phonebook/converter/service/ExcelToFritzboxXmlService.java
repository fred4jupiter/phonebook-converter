package de.fred4jupiter.phonebook.converter.service;

import de.fred4jupiter.phonebook.converter.excel.ExcelContact;
import de.fred4jupiter.phonebook.converter.excel.ExcelImportService;
import de.fred4jupiter.phonebook.converter.fritzbox.PhonebookBuilder;
import de.fred4jupiter.phonebook.converter.fritzbox.Phonebooks;
import de.fred4jupiter.phonebook.converter.xml.XmlCreatorService;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public class ExcelToFritzboxXmlService {

    @Autowired
    private ExcelImportService excelImportService;

    @Autowired
    private XmlCreatorService xmlCreatorService;

    public void readExcelFileAndConvert(String excelIn, String xmlOut) {
        List<ExcelContact> excelContacts = excelImportService.importFromExcel(new File(excelIn), this::mapToExcelContact);

        PhonebookBuilder phonebookBuilder = PhonebookBuilder.create().withName("test");
        excelContacts.forEach(contact -> {
            phonebookBuilder.withContact(contact.getName(), contact.getCompletePhoneHome(), contact.getCompletePhoneMobile());
        });

        Phonebooks phonebooks = phonebookBuilder.build();
        xmlCreatorService.createFritzboxPhonebookXmlFile(phonebooks, new File(xmlOut));
    }

    private ExcelContact mapToExcelContact(Row row) {
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
