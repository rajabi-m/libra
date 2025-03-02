package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.Book;
import ir.mahdi.libra.model.Borrow;
import ir.mahdi.libra.model.User;

import java.util.List;

public interface BorrowRepository {
    void save(Borrow borrow);

    Borrow findById(long id);

    void deleteById(long id);

    List<Book> getUserBorrows(long userId);

    List<User> getAssetBorrowers(long assetId);

    List<Borrow> findAll();

    void update(Borrow borrow);
}
