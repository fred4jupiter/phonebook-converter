package de.fred4jupiter.phonebook.converter.fritzbox;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class Telephony {

    @XmlAttribute(name = "nid")
    private Integer nid = 0;

    @XmlElement(name = "number")
    private List<Number> numbers = new ArrayList<>();

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public List<Number> getNumbers() {
        return numbers;
    }

    public void addNumber(String value, NumberType numberType) {
        Number number = new Number(numbers.size(), value, numberType);
        this.numbers.add(number);
        nid++;

        if (numbers.size() == 1) {
            number.setPrio(1);
        }
    }
}
