package de.fred4jupiter.phonebook.converter;

import de.fred4jupiter.phonebook.converter.fritzbox.Contact;
import de.fred4jupiter.phonebook.converter.fritzbox.Person;
import de.fred4jupiter.phonebook.converter.fritzbox.Phonebook;
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
        Phonebooks phonebooks = new Phonebooks();

        Phonebook phonebook = new Phonebook();
        phonebooks.getPhonebooks().add(phonebook);

        Contact contact = new Contact();

        Person person = new Person();
        person.setRealName("Fred Feuerstein");
        contact.setPerson(person);

        phonebook.getContacts().add(contact);

        xmlCreatorService.createXmlFile(phonebooks, new File("target/output.xml"));
    }
}
