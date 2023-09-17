package com.example.book.support.utils;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class StringUtils {
    public static boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    public static boolean isNotEmpty(CharSequence... charSequences) {
        return Arrays.stream(charSequences).noneMatch(StringUtils::isEmpty);
    }
}
