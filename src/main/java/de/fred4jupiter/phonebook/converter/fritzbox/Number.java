package de.fred4jupiter.phonebook.converter.fritzbox;

public class Number {

    private Integer prio;

    private String type;

    private Integer quickdial;

    private Integer id = 0;

    public Integer getPrio() {
        return prio;
    }

    public void setPrio(Integer prio) {
        this.prio = prio;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
