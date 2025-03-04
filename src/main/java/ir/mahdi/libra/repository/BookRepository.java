package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface BookRepository extends JpaRepository<Book, Long> {
    Collection<Book> findByTitle(String title);
}
