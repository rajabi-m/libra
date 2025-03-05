package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.Book;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Collection;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Collection<Book> findByTitle(String title);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Book> findById(Long aLong);
}
