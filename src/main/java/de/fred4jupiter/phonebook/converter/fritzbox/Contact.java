package de.fred4jupiter.phonebook.converter.fritzbox;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class Contact {

    @XmlElement(name = "category")
    private Integer category = 0;

    @XmlElement(name = "person")
    private Person person;

    @XmlElement(name = "telephony")
    private Telephony telephony;

    @XmlElement(name = "services")
    private Services services;

    @XmlElement(name = "setup")
    private Setup setup;

    private Integer uniqueId;

    public Telephony getTelephony() {
        return telephony;
    }

    public void setTelephony(Telephony telephony) {
        this.telephony = telephony;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Services getServices() {
        return services;
    }

    public void setServices(Services services) {
        this.services = services;
    }

    public Setup getSetup() {
        return setup;
    }

    public void setSetup(Setup setup) {
        this.setup = setup;
    }

    public Integer getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(Integer uniqueId) {
        this.uniqueId = uniqueId;
    }
}
