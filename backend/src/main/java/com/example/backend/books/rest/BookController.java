package com.example.backend.books.rest;

import com.example.backend.books.models.BookModel;
import com.example.backend.books.services.BookService;
import com.example.commons.rest.BaseController;
import com.example.commons.models.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController extends BaseController  {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/select-all-books")
    public ApiResponse<List<BookModel>> selectBooks(){
        return successResponse(bookService.getAll());
    }
}
