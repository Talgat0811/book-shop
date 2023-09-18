package com.example.book.common.services.impl;

import com.example.book.common.entities.Book;
import com.example.book.common.models.BookModel;
import com.example.book.common.converters.BookMapper;
import com.example.book.common.repositories.BookRepository;
import com.example.book.common.services.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }

    @Override
    public List<BookModel> getAll() {
        var books = bookRepository.findAll();

        List<BookModel> bookModels = new ArrayList<>();
        for(Book book : books){
            bookModels.add(bookMapper.toModel(book));
        }
        return bookModels;
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
