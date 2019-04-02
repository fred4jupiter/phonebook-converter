package de.fred4jupiter.phonebook.converter.excel;

import org.apache.poi.ss.usermodel.Row;

@FunctionalInterface
public interface ExcelRowMapper<T> {

    T mapRow(Row row);
}
