package com.example.book.services.impl;

import com.example.book.common.models.BookModel;
import com.example.book.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    @Override
    public List<BookModel> getAll() {
        return null;
    }

    @Override
    public BookModel getById(Long id) {
        return null;
    }

    @Override
    public BookModel save(BookModel bookModel) {
        return null;
    }

    @Override
    public BookModel update(BookModel bookModel) {
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        return false;
    }
}
