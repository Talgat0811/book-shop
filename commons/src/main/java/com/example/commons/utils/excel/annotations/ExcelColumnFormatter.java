package com.example.commons.utils.excel.annotations;

import com.example.commons.utils.excel.formatters.BaseExcelColumnFormatter;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumnFormatter {
    Class<? extends BaseExcelColumnFormatter> formatter();

    String formatPattern() default "";
}
