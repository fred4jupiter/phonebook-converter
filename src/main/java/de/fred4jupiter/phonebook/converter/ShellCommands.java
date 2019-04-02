package de.fred4jupiter.phonebook.converter;

import de.fred4jupiter.phonebook.converter.service.ExcelToFritzboxXmlService;
import de.fred4jupiter.phonebook.converter.service.ExcelToVCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommands {

    @Autowired
    private ExcelToFritzboxXmlService excelToFritzboxXmlService;

    @Autowired
    private ExcelToVCardService excelToVCardService;

    @ShellMethod("Creates an Excel template file.")
    public String createExcelTemplate(@ShellOption(defaultValue = "template.xlsx") String outputFile) {
        excelToFritzboxXmlService.createExcelTemplateFile(outputFile);

        return "Created Excel template file: " + outputFile;
    }

    @ShellMethod("Converts a given Excel file to Fritzbox phonebook xml file.")
    public String convertToFritzboxPhonebook(@ShellOption String inputFile, @ShellOption(defaultValue = "phonebook.xml") String outputFile,
                                             @ShellOption(defaultValue = "test") String phonebookName,
                                             @ShellOption(defaultValue = "false") boolean internationalize) {
        excelToFritzboxXmlService.readExcelFileAndConvert(phonebookName, inputFile, outputFile, internationalize);

        return "Converted file '" + inputFile + "' to Fritzbox phonebook XML file '" + outputFile + "'";
    }

    @ShellMethod("Converts a given Excel file to VCard files.")
    public String convertToVCard(@ShellOption String inputFile, @ShellOption String outputFolder, @ShellOption(defaultValue = "false") boolean internationalize) {
        excelToVCardService.readExcelFileAndConvertToVCard(inputFile, outputFolder, internationalize);

        return "Converted file '" + inputFile + "' to VCard files in  folder '" + outputFolder + "'";
    }
}
