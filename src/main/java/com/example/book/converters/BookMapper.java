package com.example.book.converters;

import com.example.book.common.entities.Book;
import com.example.book.common.models.BookModel;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookModel toModel(Book book) {
        return BookModel.builder()
                .id(book.getId())
                .build();
    }

    public Book toEntity(BookModel bookModel) {
        Book book = new Book();
        book.setId(bookModel.getId());
        return book;
    }
}
