package de.fred4jupiter.phonebook.converter.fritzbox;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "phonebooks")
public class Phonebooks {

    @XmlElement(name = "phonebook")
    private List<Phonebook> phonebooks = new ArrayList<>();

    public List<Phonebook> getPhonebooks() {
        return phonebooks;
    }

    public void setPhonebooks(List<Phonebook> phonebooks) {
        this.phonebooks = phonebooks;
    }
}
