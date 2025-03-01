package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.CreateBookDto;

import java.util.List;

public interface BookCrudService {
    BookDto createBook(CreateBookDto createBookDto);
    BookDto getBook(Long id);
    BookDto updateBook(Long id, CreateBookDto createBookDto);
    void deleteBook(Long id);
    List<BookDto> getAllBooks();
}
