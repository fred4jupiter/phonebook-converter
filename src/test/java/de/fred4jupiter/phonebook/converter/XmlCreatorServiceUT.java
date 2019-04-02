package de.fred4jupiter.phonebook.converter;

import de.fred4jupiter.phonebook.converter.fritzbox.*;
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
        Phonebooks phonebooks = new Phonebooks();

        Phonebook phonebook = new Phonebook();
        phonebook.setName("test");
        phonebooks.getPhonebooks().add(phonebook);

        Contact contact = new Contact();

        Person person = new Person();
        person.setRealName("Fred Feuerstein");
        contact.setPerson(person);

        Telephony telephony = new Telephony();
        telephony.addNumber("01234354787", NumberType.home);
        telephony.addNumber("0171345654", NumberType.mobile);

        contact.setTelephony(telephony);

        phonebook.getContacts().add(contact);

        xmlCreatorService.createXmlFile(phonebooks, new File("target/output.xml"));
    }
}
