package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.IdNotFoundException;
import ir.mahdi.libra.model.Asset;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.utils.ReflectionUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Component
public class DefaultBookRepository implements BookRepository {
    private final HashMap<Long, Book> books = new HashMap<>();
    private long idCounter = 0;
    private final Environment environment;

    public DefaultBookRepository(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void save(Book book) {
        if (book == null) {
            throw new NullPointerException();
        }
        idCounter++;
        long bookId = idCounter;
        ReflectionUtils.setId(book, bookId, Asset.class);
        books.put(bookId, book);
    }

    @Override
    public Book findById(long id) {
        if (!books.containsKey(id)) {
            throw new IdNotFoundException("Cannot find book with id: " + id);
        }
        return books.get(id);
    }

    @Override
    public void deleteById(long id) {
        if (!books.containsKey(id)) {
            throw new IdNotFoundException("Cannot find book with id: " + id);
        }
        books.remove(id);
    }

    @Override
    public List<Book> findAll() {
        return books.values().stream().toList();
    }

    @Override
    public void update(Book book) {
        if (book == null) {
            throw new NullPointerException();
        }
        if (!books.containsKey(book.getId())) {
            throw new IdNotFoundException("Cannot find book with id: " + book.getId());
        }
        books.put(book.getId(), book);
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return books.values().stream().filter(book -> book.getTitle().equals(title)).toList();
    }

    @PostConstruct
    public void init() {
        if (!Objects.equals(environment.getProperty("application.debug"), "true")) {
            return;
        }
        save(new Book("The Great Gatsby", "F. Scott Fitzgerald", 1925));
        save(new Book("To Kill a Mockingbird", "Harper Lee", 1960));
        save(new Book("1984", "George Orwell", 1949));
        save(new Book("Pride and Prejudice", "Jane Austen", 1813));
        save(new Book("The Catcher in the Rye", "J.D. Salinger", 1951));
        save(new Book("The Lord of the Rings", "J.R.R. Tolkien", 1954));
        save(new Book("Animal Farm", "George Orwell", 1945));
        save(new Book("The Hobbit", "J.R.R. Tolkien", 1937));
        save(new Book("The Little Prince", "Antoine de Saint-Exupéry", 1943));
        save(new Book("Brave New World", "Aldous Huxley", 1932));
    }
}
