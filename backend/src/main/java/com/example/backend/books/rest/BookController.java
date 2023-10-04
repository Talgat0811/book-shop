package com.example.backend.books.rest;

import com.example.backend.books.models.BookModel;
import com.example.backend.books.services.BookService;
import com.example.commons.enums.ResultCode;
import com.example.commons.exceptions.NotFoundException;
import com.example.commons.rest.BaseController;
import com.example.commons.models.ApiResponse;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController extends BaseController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/get-books")
    public ApiResponse<List<BookModel>> getBooks() {
        System.out.println(1);
        return successResponse(bookService.getAll());
    }

    @DeleteMapping("/delete-book/{bookId}")
    public ApiResponse<BookModel> deleteBook(@PathVariable("bookId") Long bookId) {
        try {
            boolean deleted = bookService.deleteById(bookId);

            if (deleted) {
                return successResponse(bookService.getById(bookId));
            } else {
                // Если книга не была удалена, можно бросить исключение
                throw new Exception("Не удалось удалить книгу с id " + bookId);
            }
        } catch (Exception e) {
            // Здесь можно обработать исключение и вернуть ApiResponse с информацией об ошибке
            return errorResponse("Произошла ошибка при удалении книги: " + e.getMessage());
        }
    }

    @PostMapping("/add-book")
    public ApiResponse<BookModel> addBook(@RequestBody BookModel book) {
        try {
            // Здесь вы можете вызвать ваш сервис для добавления новой книги
            BookModel addedBook = bookService.save(book);
            System.out.println(1);

            if (addedBook != null) {
                System.out.println(2);
                return successResponse(addedBook);

            } else {
                System.out.println(3);
                throw new Exception("Не удалось добавить книгу");
            }
        } catch (Exception e) {
            // Возвращаем ApiResponse с информацией об ошибке
            System.out.println(4);
            return errorResponse("Произошла ошибка при добавлении книги: " + e.getMessage());
        }
    }

    @GetMapping("/get-book-by-id/{id}")
    public ApiResponse<BookModel> getById(@PathVariable("id") Long bookId) {
        try {
            BookModel book = bookService.getById(bookId);

            if (book != null) {
                return successResponse(book);
            } else {
                throw new NotFoundException();
            }
        } catch (Exception e) {
            // Здесь можно обработать исключение и вернуть ApiResponse с информацией об ошибке
            return errorResponse("Произошла ошибка при получении информации о книге: " + e.getMessage());
        }
    }

    public ApiResponse<BookModel> errorResponse(String errorMessage) {
        ApiResponse<BookModel> response = new ApiResponse<>(null, ResultCode.EXCEPTION);
        response.setDetails(errorMessage);
        return response;
    }
}