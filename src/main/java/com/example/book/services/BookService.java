package com.example.book.services;

import com.example.book.common.models.BookModel;

import java.util.List;

public interface BookService {
    List<BookModel> getAll();
    BookModel getById(Long id);
    BookModel save(BookModel bookModel);
    BookModel update(BookModel bookModel);

    boolean deleteById(Long id);
}
