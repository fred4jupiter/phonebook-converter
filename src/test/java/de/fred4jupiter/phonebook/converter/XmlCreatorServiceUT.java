package de.fred4jupiter.phonebook.converter;

import de.fred4jupiter.phonebook.converter.fritzbox.PhonebookBuilder;
import de.fred4jupiter.phonebook.converter.fritzbox.Phonebooks;
import de.fred4jupiter.phonebook.converter.xml.XmlCreatorService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class XmlCreatorServiceUT {

    @InjectMocks
    private XmlCreatorService xmlCreatorService;

    @Test
    public void createXmlOutput() {
        Phonebooks phonebooks = PhonebookBuilder.create().withName("test").withContact("Fred Feuerstein", "01234354787", "0171345654").build();

        xmlCreatorService.createFritzboxPhonebookXmlFile(phonebooks, new File("target/output.xml"));
    }
}
