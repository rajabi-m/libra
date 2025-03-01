package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.IdNotFoundException;
import ir.mahdi.libra.model.Book;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

import static ir.mahdi.libra.utils.ReflectionUtils.changeAssetId;

@Component
public class DefaultBookRepository implements BookRepository {
    private final HashMap<Long, Book> books = new HashMap<>();

    @Override
    public void save(Book book) {
        if (book == null){
            throw new NullPointerException();
        }
        long bookId = book.getId() == null ? books.size() + 1 : book.getId();
        changeAssetId(book, bookId);
        books.put(bookId, book);
    }

    @Override
    public Book findById(Long id) {
        if (!books.containsKey(id)){
            throw new IdNotFoundException("Cannot find book with id: " + id);
        }
        return books.get(id);
    }

    @Override
    public List<Book> findAll() {
        return books.values().stream().toList();
    }

    @Override
    public void deleteById(Long id) {
        if (!books.containsKey(id)){
            throw new IdNotFoundException("Cannot find book with id: " + id);
        }
        books.remove(id);
    }

    @Override
    public void update(Book book) {
        if (book == null){
            throw new NullPointerException();
        }
        if (!books.containsKey(book.getId())){
            throw new IdNotFoundException("Cannot find book with id: " + book.getId());
        }
        books.put(book.getId(), book);
    }
}
