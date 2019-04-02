package de.fred4jupiter.phonebook.converter.fritzbox;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
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
            telephony.addNumber(internationalizeNumber(phoneHome, internationalize), NumberType.home);
        }
        if (StringUtils.isNotBlank(phoneMobile)) {
            telephony.addNumber(internationalizeNumber(phoneMobile, internationalize), NumberType.mobile);
        }

        contact.setTelephony(telephony);

        phonebook.addContact(contact);
        return this;
    }

    private String internationalizeNumber(String phoneNumber, boolean internationalize) {
        if (internationalize) {
            PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
            try {
                Phonenumber.PhoneNumber swissNumberProto = phoneUtil.parse(phoneNumber, "DE");
                return phoneUtil.format(swissNumberProto, PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            } catch (NumberParseException e) {
                LOG.error("Cloud not i18n number=" + phoneNumber + ". cause: " + e.getMessage());
            }
        }

        return phoneNumber;
    }

    public Phonebooks build() {
        Phonebooks phonebooks = new Phonebooks();
        phonebooks.addPhonebook(phonebook);

        return phonebooks;
    }
}
