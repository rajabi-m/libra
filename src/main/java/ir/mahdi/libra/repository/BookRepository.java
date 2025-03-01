package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.Book;

import java.util.List;

public interface BookRepository {
    void save(Book book);
    Book findById(Long id);
    List<Book> findAll();
    void deleteById(Long id);
    void update(Book book);
}
