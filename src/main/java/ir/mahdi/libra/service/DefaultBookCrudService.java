package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.CreateBookDto;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultBookCrudService implements BookCrudService {
    private final BookRepository bookRepository;

    public DefaultBookCrudService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public BookDto createBook(CreateBookDto createBookDto) {
        Book book = new Book(createBookDto.getTitle(), createBookDto.getAuthor(), createBookDto.getReleaseYear());
        bookRepository.save(book);
        return BookDto.of(book);
    }

    @Override
    public BookDto getBook(Long id) {
        Book book = bookRepository.findById(id);
        if (book == null){
            return null;
        }
        return BookDto.of(book);
    }

    @Override
    public BookDto updateBook(Long id, CreateBookDto createBookDto) {
        Book book = bookRepository.findById(id);
        if (book == null){
            return null;
        }
        book.setTitle(createBookDto.getTitle());
        book.setAuthor(createBookDto.getAuthor());
        book.setReleaseYear(createBookDto.getReleaseYear());
        bookRepository.update(book);
        return BookDto.of(book);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(BookDto::of).toList();
    }
}
