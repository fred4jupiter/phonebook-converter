package de.fred4jupiter.phonebook.converter.fritzbox;

import java.util.ArrayList;
import java.util.List;

public class Phonebook {

    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }
}
