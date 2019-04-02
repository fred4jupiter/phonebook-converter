package de.fred4jupiter.phonebook.converter;

import de.fred4jupiter.phonebook.converter.service.ExcelToFritzboxXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommands {

    @Autowired
    private ExcelToFritzboxXmlService excelToFritzboxXmlService;

    @ShellMethod("Creates an Excel template file.")
    public String createExcelTemplate(@ShellOption(defaultValue = "template.xlsx") String outputFile) {
        excelToFritzboxXmlService.createExcelTemplateFile(outputFile);

        return "Created Excel template file: " + outputFile;
    }

    @ShellMethod("Converts a given Excel file to Fritzbox phonebook xml file.")
    public String convertToFritzboxPhonebook(@ShellOption String inputFile, @ShellOption(defaultValue = "phonebook.xml") String outputFile) {
        excelToFritzboxXmlService.readExcelFileAndConvert(inputFile, outputFile);

        return "Converted file '" + inputFile + "' to Fritzbox phonebook XML file '" + outputFile + "'";
    }
}
