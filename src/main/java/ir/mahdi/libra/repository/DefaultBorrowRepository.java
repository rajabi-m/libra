package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.IdNotFoundException;
import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.model.Borrow;
import ir.mahdi.libra.utils.ReflectionUtils;

import java.util.HashMap;
import java.util.List;

public class DefaultBorrowRepository implements BorrowRepository {
    private final HashMap<Long, Borrow> borrows = new HashMap<>();

    private final BookRepository bookRepository;

    public DefaultBorrowRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Borrow borrow) {
        if (borrow == null) {
            throw new NullPointerException();
        }
        long borrowId = borrow.getId() == -1 ? borrows.size() + 1 : borrow.getId();
        ReflectionUtils.changeId(borrow, borrowId);
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
                .map(borrow -> bookRepository.findById(borrow.getAssetId()))
                .toList();
    }

    @Override
    public List<Borrow> getAssetBorrows(long assetId) {
        return borrows.values().parallelStream().filter(borrow -> borrow.getAssetId() == assetId).toList();
    }

    @Override
    public List<Borrow> findAll() {
        return borrows.values().stream().toList();
    }
}
