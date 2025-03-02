package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.CreateBookDto;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class DefaultBookManagerService implements BookManagerService {
    private final BookRepository bookRepository;
    private final Environment environment;

    public DefaultBookManagerService(BookRepository bookRepository, Environment environment) {
        this.bookRepository = bookRepository;
        this.environment = environment;
    }

    @Override
    public BookDto createBook(CreateBookDto createBookDto) {
        Book book = new Book(createBookDto.getTitle(), createBookDto.getAuthor(), createBookDto.getReleaseYear());
        bookRepository.save(book);
        return BookDto.of(book);
    }

    @Override
    public BookDto getBook(long id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            return null;
        }
        return BookDto.of(book);
    }

    @Override
    public BookDto updateBook(long id, CreateBookDto createBookDto) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            return null;
        }
        book.setTitle(createBookDto.getTitle());
        book.setAuthor(createBookDto.getAuthor());
        book.setReleaseYear(createBookDto.getReleaseYear());
        bookRepository.update(book);
        return BookDto.of(book);
    }

    @Override
    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll().stream().map(BookDto::of).toList();
    }

    @Override
    public List<BookDto> getAllBooksSortByReleaseYear() {
        return bookRepository.findAllSortByReleaseYear().stream().map(BookDto::of).toList();
    }

    @Override
    public List<BookDto> findBooksByTitle(String title) {
        return bookRepository.findBooksByTitle(title).stream().map(BookDto::of).toList();
    }

    @PostConstruct
    public void init() {
        if (!Objects.equals(environment.getProperty("application.debug"), "true")) {
            return;
        }

        createBook(new CreateBookDto("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        createBook(new CreateBookDto("To Kill a Mockingbird", "Harper Lee", 1960));
        createBook(new CreateBookDto("1984", "George Orwell", 1949));
        createBook(new CreateBookDto("Animal Farm", "George Orwell", 1945));
        createBook(new CreateBookDto("Brave New World", "Aldous Huxley", 1932));
    }
}
