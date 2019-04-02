package de.fred4jupiter.phonebook.converter.fritzbox;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
public class Number {

    @XmlAttribute(name = "prio")
    private Integer prio;

    @XmlAttribute(name = "type")
    private NumberType numberType;

    @XmlAttribute(name = "quickdial")
    private Integer quickdial;

    @XmlAttribute(name = "id")
    private Integer id = 0;

    @XmlValue
    private String value;

    public Number(Integer id, String value, NumberType numberType) {
        this.id = id;
        this.value = value;
        this.numberType = numberType;
    }

    public String getValue() {
        return value;
    }

    public Integer getPrio() {
        return prio;
    }

    public void setPrio(Integer prio) {
        this.prio = prio;
    }

    public NumberType getNumberType() {
        return numberType;
    }

    public void setNumberType(NumberType numberType) {
        this.numberType = numberType;
    }

    public Integer getQuickdial() {
        return quickdial;
    }

    public void setQuickdial(Integer quickdial) {
        this.quickdial = quickdial;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
