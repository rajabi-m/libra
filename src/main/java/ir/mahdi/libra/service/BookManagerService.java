package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.CreateBookDto;

import java.util.List;

public interface BookManagerService {
    BookDto createBook(CreateBookDto createBookDto);

    BookDto getBook(long id);

    BookDto updateBook(long id, CreateBookDto createBookDto);

    void deleteBook(long id);

    List<BookDto> getAllBooks();

    List<BookDto> getAllBooksSortByReleaseYear();

    List<BookDto> findBooksByTitle(String title);
}
