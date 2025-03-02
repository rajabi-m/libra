package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.NotFoundException;
import ir.mahdi.libra.model.Asset;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.utils.ReflectionUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

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
            throw new NotFoundException("Cannot find book with id: " + id);
        }
        return books.get(id);
    }

    @Override
    public void deleteById(long id) {
        if (!books.containsKey(id)) {
            throw new NotFoundException("Cannot find book with id: " + id);
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
            throw new NotFoundException("Cannot find book with id: " + book.getId());
        }
        books.put(book.getId(), book);
    }

    @Override
    public List<Book> findAllSortByReleaseYear() {
        return books.values().stream()
                .sorted(Comparator.comparingInt(Book::getReleaseYear))
                .toList();
    }

    @Override
    public List<Book> findBooksByTitle(String title) {
        return books.values().stream().filter(book -> book.getTitle().equals(title)).toList();
    }
}
