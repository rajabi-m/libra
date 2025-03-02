package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.IdNotFoundException;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.model.Borrow;
import ir.mahdi.libra.model.User;
import ir.mahdi.libra.utils.ReflectionUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultBorrowRepository implements BorrowRepository {
    private final HashMap<Long, Borrow> borrows = new HashMap<>();
    private long idCounter = 0;
    private final Environment environment;

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public DefaultBorrowRepository(Environment environment, BookRepository bookRepository, UserRepository userRepository) {
        this.environment = environment;
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void save(Borrow borrow) {
        if (borrow == null) {
            throw new NullPointerException();
        }
        // Check if user and book exist
        try {
            userRepository.findById(borrow.getUserId());
            bookRepository.findById(borrow.getBookId());
        } catch (IdNotFoundException e) {
            throw new IdNotFoundException(e);
        }

        idCounter++;
        long borrowId = idCounter;
        ReflectionUtils.setId(borrow, borrowId, Borrow.class);
        ReflectionUtils.setLocalDateFieldToNow(borrow, "borrowDate");
        borrows.put(borrowId, borrow);
    }

    @Override
    public Borrow findById(long id) {
        if (!borrows.containsKey(id)) {
            throw new IdNotFoundException("Cannot find borrow with id: " + id);
        }
        return borrows.get(id);
    }

    @Override
    public void deleteById(long id) {
        if (!borrows.containsKey(id)) {
            throw new IdNotFoundException("Cannot find borrow with id: " + id);
        }
        borrows.remove(id);
    }

    @Override
    public List<Book> getUserBorrows(long userId) {
        return borrows.values().parallelStream()
                .filter(borrow -> borrow.getUserId() == userId)
                .map(borrow -> bookRepository.findById(borrow.getBookId()))
                .toList();
    }

    @Override
    public List<User> getAssetBorrowers(long assetId) {
        return borrows.values().parallelStream()
                .filter(borrow -> borrow.getBookId() == assetId)
                .map(borrow -> userRepository.findById(borrow.getId()))
                .toList();
    }

    @Override
    public List<Borrow> findAll() {
        return borrows.values().stream().toList();
    }

    @Override
    public void update(Borrow borrow) {
        if (borrow == null) {
            throw new NullPointerException();
        }
        if (!borrows.containsKey(borrow.getId())) {
            throw new IdNotFoundException("Cannot find borrow with id: " + borrow.getId());
        }
        borrows.put(borrow.getId(), borrow);
    }

    @PostConstruct
    public void init() {
        if (!Objects.equals(environment.getProperty("application.debug"), "true")) {
            return;
        }
        // Add some initial borrows
        Borrow borrow1 = new Borrow(1, 1, LocalDate.now().plusDays(2));
        Borrow borrow2 = new Borrow(2, 2, LocalDate.now().plusDays(1));
        Borrow borrow3 = new Borrow(3, 3, LocalDate.now().plusDays(12));
        Borrow borrow4 = new Borrow(4, 4, LocalDate.now().plusDays(22));
        Borrow borrow5 = new Borrow(5, 5, LocalDate.now().plusDays(33));
        save(borrow1);
        save(borrow2);
        save(borrow3);
        save(borrow4);
        save(borrow5);
    }
}
