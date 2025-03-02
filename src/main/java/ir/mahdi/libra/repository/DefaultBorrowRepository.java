package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.NotFoundException;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.model.Borrow;
import ir.mahdi.libra.model.User;
import ir.mahdi.libra.utils.ReflectionUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

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
        } catch (NotFoundException e) {
            throw new NotFoundException(e);
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
            throw new NotFoundException("Cannot find borrow with id: " + id);
        }
        return borrows.get(id);
    }

    @Override
    public void deleteById(long id) {
        if (!borrows.containsKey(id)) {
            throw new NotFoundException("Cannot find borrow with id: " + id);
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
            throw new NotFoundException("Cannot find borrow with id: " + borrow.getId());
        }
        borrows.put(borrow.getId(), borrow);
    }
}
