package de.fred4jupiter.phonebook.converter.service;

import de.fred4jupiter.phonebook.converter.excel.ExcelContact;
import de.fred4jupiter.phonebook.converter.excel.ExcelImportService;
import de.fred4jupiter.phonebook.converter.util.PhoneNumberUtil;
import ezvcard.Ezvcard;
import ezvcard.VCard;
import ezvcard.VCardVersion;
import ezvcard.parameter.AddressType;
import ezvcard.parameter.TelephoneType;
import ezvcard.property.Address;
import ezvcard.property.StructuredName;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class ExcelToVCardService {

    private static final Logger LOG = LoggerFactory.getLogger(ExcelToVCardService.class);

    @Autowired
    private ExcelImportService excelImportService;

    @Autowired
    private ExcelContactMapper excelContactMapper;

    public void readExcelFileAndConvertToVCard(String excelIn, String outFolder, boolean internationalize) {
        List<ExcelContact> excelContacts = excelImportService.importFromExcel(new File(excelIn), row -> excelContactMapper.mapToExcelContact(row));

        excelContacts.forEach(excelContact -> {
            VCard vcard = new VCard();

            StructuredName structuredName = new StructuredName();
            structuredName.setFamily(excelContact.getLastname());
            structuredName.setGiven(excelContact.getFirstname());
            vcard.setStructuredName(structuredName);
            vcard.setFormattedName(excelContact.getName());

            if (StringUtils.isNotBlank(excelContact.getCompletePhoneHome())) {
                vcard.addTelephoneNumber(PhoneNumberUtil.getInstance().internationalizeNumber(excelContact.getCompletePhoneHome(), internationalize), TelephoneType.HOME);
            }
            if (StringUtils.isNotBlank(excelContact.getCompletePhoneMobile())) {
                vcard.addTelephoneNumber(PhoneNumberUtil.getInstance().internationalizeNumber(excelContact.getCompletePhoneMobile(), internationalize), TelephoneType.CELL);
            }

            if (excelContact.hasAddress()) {
                Address address = new Address();
                address.setStreetAddress(excelContact.getStreet());
                address.setPostalCode(excelContact.getPostalCode());
                address.setRegion(excelContact.getCity());
                address.getTypes().add(AddressType.HOME);
                vcard.addAddress(address);
            }

            String vcardContent = Ezvcard.write(vcard).version(VCardVersion.V4_0).go();
            try {
                FileUtils.writeStringToFile(new File(outFolder + File.separator + excelContact.getName() + ".vcf"), vcardContent, "UTF-8");
            } catch (IOException e) {
                LOG.error(e.getMessage());
            }
        });
    }
}
