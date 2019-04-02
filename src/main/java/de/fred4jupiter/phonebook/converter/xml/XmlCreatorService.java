package de.fred4jupiter.phonebook.converter.xml;

import de.fred4jupiter.phonebook.converter.fritzbox.Phonebooks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

@Service
public class XmlCreatorService {

    private static final Logger LOG = LoggerFactory.getLogger(XmlCreatorService.class);

    public void createFritzboxPhonebookXmlFile(Phonebooks phonebooks, File outputFile) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Phonebooks.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(phonebooks, outputFile);
//            marshaller.marshal(phonebooks, System.out);
        }   catch(JAXBException e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
