package de.fred4jupiter.phonebook.converter.excel;

public class ExcelContact {

    private String name;

    private String phonePrefixHome;

    private String phoneHome;

    private String phonePrefixMobile;

    private String phoneMobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhonePrefixHome() {
        return phonePrefixHome;
    }

    public void setPhonePrefixHome(String phonePrefixHome) {
        this.phonePrefixHome = phonePrefixHome;
    }

    public String getPhoneHome() {
        return phoneHome;
    }

    public void setPhoneHome(String phoneHome) {
        this.phoneHome = phoneHome;
    }

    public String getPhonePrefixMobile() {
        return phonePrefixMobile;
    }

    public void setPhonePrefixMobile(String phonePrefixMobile) {
        this.phonePrefixMobile = phonePrefixMobile;
    }

    public String getPhoneMobile() {
        return phoneMobile;
    }

    public void setPhoneMobile(String phoneMobile) {
        this.phoneMobile = phoneMobile;
    }

    public String getCompletePhoneHome() {
        return phonePrefixHome + phoneHome;
    }

    public String getCompletePhoneMobile() {
        return phonePrefixMobile + phoneMobile;
    }

    public String getFirstname() {
        if (name.contains(" ")) {
            String[] splitted = name.split(" ");
            return splitted[0];
        }

        return name;
    }

    public String getLastname() {
        if (name.contains(" ")) {
            String[] splitted = name.split(" ");
            return splitted[1];
        }

        return name;
    }
}
