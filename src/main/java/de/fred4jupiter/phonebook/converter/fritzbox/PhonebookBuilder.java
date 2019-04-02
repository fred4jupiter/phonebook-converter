package de.fred4jupiter.phonebook.converter.fritzbox;

import org.apache.commons.lang3.StringUtils;

public class PhonebookBuilder {

    private final Phonebook phonebook;

    private PhonebookBuilder() {
        phonebook = new Phonebook();
    }

    public static PhonebookBuilder create() {
        return new PhonebookBuilder();
    }

    public PhonebookBuilder withName(String phonebookName) {
        phonebook.setName(phonebookName);
        return this;
    }

    public PhonebookBuilder withContact(String name, String phoneHome, String phoneMobile) {
        Contact contact = new Contact();

        Person person = new Person();
        person.setRealName(name);
        contact.setPerson(person);

        Telephony telephony = new Telephony();
        if (StringUtils.isNotBlank(phoneHome)) {
            telephony.addNumber(phoneHome, NumberType.home);
        }
        if (StringUtils.isNotBlank(phoneMobile)) {
            telephony.addNumber(phoneMobile, NumberType.mobile);
        }

        contact.setTelephony(telephony);

        phonebook.addContact(contact);
        return this;
    }

    public Phonebooks build() {
        Phonebooks phonebooks = new Phonebooks();
        phonebooks.addPhonebook(phonebook);

        return phonebooks;
    }
}
