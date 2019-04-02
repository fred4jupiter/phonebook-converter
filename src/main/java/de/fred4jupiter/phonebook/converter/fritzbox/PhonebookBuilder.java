package de.fred4jupiter.phonebook.converter.fritzbox;

import de.fred4jupiter.phonebook.converter.util.PhoneNumberUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhonebookBuilder {

    private static final Logger LOG = LoggerFactory.getLogger(PhonebookBuilder.class);

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
        return withContact(name, phoneHome, phoneMobile, false);
    }

    public PhonebookBuilder withContact(String name, String phoneHome, String phoneMobile, boolean internationalize) {
        Contact contact = new Contact();

        Person person = new Person();
        person.setRealName(name);
        contact.setPerson(person);

        Telephony telephony = new Telephony();
        if (StringUtils.isNotBlank(phoneHome)) {
            telephony.addNumber(PhoneNumberUtil.getInstance().internationalizeNumber(phoneHome, internationalize), NumberType.home);
        }
        if (StringUtils.isNotBlank(phoneMobile)) {
            telephony.addNumber(PhoneNumberUtil.getInstance().internationalizeNumber(phoneMobile, internationalize), NumberType.mobile);
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
