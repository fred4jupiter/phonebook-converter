# phonebook-converter
Command line tool for creating Fritzbox Phonebook XML file from Excel.

## Usage

### Build from the sources

`mvn clean install`

In the target dir you find the fat jar like `phonebook-converter-0.0.1-SNAPSHOT.jar`.

### Run the binary shell application 

`java -jar phonebook-converter-0.0.1-SNAPSHOT.jar`

After that you can see the available commands with entering `help` and `enter`.

### Create Excel template file

`create-excel-template`

### Convert Excel phonebook into Fritzbox phonebook XML file

`convert-to-fritzbox-phonebook --input-file template.xlsx`

## Compatibility

Tested with Fritzbox 7490.

