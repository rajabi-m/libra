package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.Book;

import java.util.List;

public interface BookRepository {
    void save(Book book);

    Book findById(long id);

    void deleteById(long id);

    List<Book> findAll();

    void update(Book book);

    List<Book> findBooksByTitle(String title);
}
