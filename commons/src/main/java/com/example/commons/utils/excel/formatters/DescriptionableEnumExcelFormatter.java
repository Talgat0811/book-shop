package com.example.commons.utils.excel.formatters;

import com.example.commons.utils.excel.DescriptionContainingEnum;

public class DescriptionableEnumExcelFormatter extends BaseExcelColumnFormatter {
    public DescriptionableEnumExcelFormatter(String format) { super(format); }

    @Override
    public String formatValue(Object columnValue) {
        if (!(columnValue instanceof DescriptionContainingEnum)) return String.valueOf(columnValue);

        return ((DescriptionContainingEnum) columnValue).getDescription();
    }
}
