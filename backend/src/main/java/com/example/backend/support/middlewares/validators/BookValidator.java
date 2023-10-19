package com.example.backend.support.middlewares.validators;

import com.example.backend.books.models.BookModel;
import com.example.backend.support.annotations.TypedValidator;
import com.example.backend.support.enums.ValidatorType;
import com.example.commons.exceptions.ValidationException;
import com.example.commons.utils.BaseValidator;
import org.springframework.stereotype.Component;

import static com.example.commons.utils.StringUtils.isEmpty;

@Component
@TypedValidator(value = ValidatorType.BOOK_MODEL)
public class BookValidator implements BaseValidator {

    @Override
    public void verify(Object object) throws ValidationException {
        BookModel bookModel = (BookModel) object;

        if (isEmpty(bookModel.getName()))
            throw new ValidationException("book name is required");
    }
}
