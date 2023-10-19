package com.example.commons.utils;

import com.example.commons.exceptions.ValidationException;

public interface BaseValidator {
    void verify(Object object) throws ValidationException;
}
