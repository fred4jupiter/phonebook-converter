package de.fred4jupiter.phonebook.converter.util;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.Phonenumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PhoneNumberUtil {

    private static final Logger LOG = LoggerFactory.getLogger(PhoneNumberUtil.class);

    private static final PhoneNumberUtil INSTANCE = new PhoneNumberUtil();

    private PhoneNumberUtil() {
        // private
    }

    public static PhoneNumberUtil getInstance() {
        return INSTANCE;
    }

    public String internationalizeNumber(String phoneNumber, boolean internationalize) {
        if (internationalize) {
            com.google.i18n.phonenumbers.PhoneNumberUtil phoneUtil = com.google.i18n.phonenumbers.PhoneNumberUtil.getInstance();
            try {
                Phonenumber.PhoneNumber phone = phoneUtil.parse(phoneNumber, "DE");
                return phoneUtil.format(phone, com.google.i18n.phonenumbers.PhoneNumberUtil.PhoneNumberFormat.INTERNATIONAL);
            } catch (NumberParseException e) {
                LOG.error("Cloud not i18n number=" + phoneNumber + ". cause: " + e.getMessage());
            }
        }

        return phoneNumber;
    }
}
