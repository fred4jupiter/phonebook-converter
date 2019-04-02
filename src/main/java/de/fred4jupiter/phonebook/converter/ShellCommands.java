package de.fred4jupiter.phonebook.converter;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class ShellCommands {

    @ShellMethod("Converts a given Excel file to Fritzbox phonebook xml file.")
    public String convertToFritzboxPhonebook(@ShellOption String inputFile) {
        return "Converted file '" + inputFile + "'";
    }
}
