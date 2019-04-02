package de.fred4jupiter.phonebook.converter.service;

import de.fred4jupiter.phonebook.converter.excel.ExcelContact;
import de.fred4jupiter.phonebook.converter.excel.ExcelImportService;
import de.fred4jupiter.phonebook.converter.fritzbox.PhonebookBuilder;
import de.fred4jupiter.phonebook.converter.fritzbox.Phonebooks;
import de.fred4jupiter.phonebook.converter.xml.XmlCreatorService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelToFritzboxXmlService {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelToFritzboxXmlService.class);

    @Autowired
    private ExcelImportService excelImportService;

    @Autowired
    private XmlCreatorService xmlCreatorService;

    @Value("classpath:/template/phonebook_template.xlsx")
    private Resource excelTemplateFile;

    @Autowired
    private ExcelContactMapper excelContactMapper;

    public void readExcelFileAndConvert(String phonebookName, String excelIn, String xmlOut, boolean internationalize) {
        List<ExcelContact> excelContacts = excelImportService.importFromExcel(new File(excelIn), row -> excelContactMapper.mapToExcelContact(row));

        PhonebookBuilder phonebookBuilder = PhonebookBuilder.create().withName(phonebookName);
        excelContacts.forEach(contact -> {
            phonebookBuilder.withContact(contact.getName(), contact.getCompletePhoneHome(), contact.getCompletePhoneMobile(), internationalize);
        });

        Phonebooks phonebooks = phonebookBuilder.build();
        xmlCreatorService.createFritzboxPhonebookXmlFile(phonebooks, new File(xmlOut));
    }

    public void createExcelTemplateFile(String outputFile) {
        try {
            FileUtils.copyInputStreamToFile(excelTemplateFile.getInputStream(), new File(outputFile));
        } catch (IOException e) {
            LOG.error(e.getMessage());
        }
    }
}
