package com.example.backend.books.converters;

import com.example.backend.books.entities.Book;
import com.example.backend.books.models.BookModel;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookModel toModel(Book book) {
        return BookModel.builder()
                .id(book.getId())
                .name(book.getName())
                .build();
    }

    public Book toEntity(BookModel bookModel) {
        Book book = new Book();
        book.setId(bookModel.getId());
        book.setName(bookModel.getName());
        return book;
    }
}
