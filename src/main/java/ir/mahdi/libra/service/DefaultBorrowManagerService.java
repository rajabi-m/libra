package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.*;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.model.Borrow;
import ir.mahdi.libra.repository.BookRepository;
import ir.mahdi.libra.repository.BorrowRepository;
import ir.mahdi.libra.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@DependsOn({"defaultBookManagerService", "defaultUserManagerService"})
//TODO: temporary solution for creating initial borrows
public class DefaultBorrowManagerService implements BorrowManagerService {
    private final BorrowRepository borrowRepository;
    private final BookRepository bookRepository;
    private final UserRepository userRepository;
    private final Environment environment;

    public DefaultBorrowManagerService(BorrowRepository borrowRepository, BookRepository bookRepository, UserRepository userRepository, Environment environment) {
        this.borrowRepository = borrowRepository;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
        this.environment = environment;
    }

    @Override
    public List<BorrowDto> getAllBorrows() {
        return borrowRepository.findAll().parallelStream().map(BorrowDto::of).toList();
    }

    @Override
    public BorrowDto getBorrowById(Long id) {
        return BorrowDto.of(borrowRepository.findById(id));
    }

    @Override
    public BorrowDto createBorrow(CreateBorrowDto createBorrowDto) {
        // Get user and book
        userRepository.findById(createBorrowDto.getUserId());
        Book book = bookRepository.findById(createBorrowDto.getBookId());

        book.setStatus(Book.Status.BORROWED);

        Borrow borrow = new Borrow(
                createBorrowDto.getUserId(),
                createBorrowDto.getBookId(),
                createBorrowDto.getReturnDate()
        );
        borrowRepository.save(borrow);
        bookRepository.update(book);
        return BorrowDto.of(borrow);
    }

    @Override
    public BorrowDto updateBorrow(Long id, UpdateBookDto updateBookDto) {
        Borrow borrow = borrowRepository.findById(id);
        borrow.setReturnDate(updateBookDto.getReturnDate());
        borrowRepository.update(borrow);
        return BorrowDto.of(borrow);
    }

    @Override
    public void deleteBorrow(Long id) {
        Borrow borrow = borrowRepository.findById(id);
        Book book = bookRepository.findById(borrow.getBookId());
        book.setStatus(Book.Status.AVAILABLE);
        bookRepository.update(book);
        borrowRepository.deleteById(id);
    }

    @Override
    public List<BookDto> getUserBorrows(Long userId) {
        return borrowRepository.getUserBorrows(userId).parallelStream().map(BookDto::of).toList();
    }

    @Override
    public List<UserDto> getAssetBorrowers(Long bookId) {
        return borrowRepository.getAssetBorrowers(bookId).parallelStream().map(UserDto::of).toList();
    }

    @PostConstruct
    public void init() {
        if (!Objects.equals(environment.getProperty("application.debug"), "true")) {
            return;
        }
        // Add some initial borrows
        createBorrow(new CreateBorrowDto(1L, 1L, LocalDate.now().plusDays(7)));
        createBorrow(new CreateBorrowDto(2L, 2L, LocalDate.now().plusDays(7)));
        createBorrow(new CreateBorrowDto(3L, 3L, LocalDate.now().plusDays(7)));
    }
}
