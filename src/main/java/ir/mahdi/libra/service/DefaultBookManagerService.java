package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.CreateBookDto;
import ir.mahdi.libra.exception.NotFoundException;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.repository.BookRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("Cannot find book with id: " + id);
        }
        return BookDto.of(book.get());
    }

    @Override
    public BookDto updateBook(long id, CreateBookDto createBookDto) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isEmpty()) {
            throw new NotFoundException("Cannot find book with id: " + id);
        }
        book.get().setTitle(createBookDto.getTitle());
        book.get().setAuthor(createBookDto.getAuthor());
        book.get().setReleaseYear(createBookDto.getReleaseYear());
        bookRepository.save(book.get());
        return BookDto.of(book.get());
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
        return bookRepository.findAll().stream().sorted(
                Comparator.comparingInt(Book::getReleaseYear)
        ).map(BookDto::of).toList();
    }

    @Override
    public List<BookDto> findBooksByTitle(String title) {
        return bookRepository.findByTitle(title).stream().map(BookDto::of).toList();
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
