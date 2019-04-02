package de.fred4jupiter.phonebook.converter.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.jline.InteractiveShellApplicationRunner;
import org.springframework.shell.jline.ScriptShellApplicationRunner;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = {
        ScriptShellApplicationRunner.SPRING_SHELL_SCRIPT_ENABLED + "=false",
        InteractiveShellApplicationRunner.SPRING_SHELL_INTERACTIVE_ENABLED + "=false"
})
public class ExcelToFritzboxXmlServiceMT {

    @Autowired
    private ExcelToFritzboxXmlService excelToFritzboxXmlService;

    @Test
    public void convert() {
        excelToFritzboxXmlService.readExcelFileAndConvert("test", "d://Temp1/Telefon-Verzeichnis.xlsx", "d://Temp1/phonebook.xml", false);
    }
}
