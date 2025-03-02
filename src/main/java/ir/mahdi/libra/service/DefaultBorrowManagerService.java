package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.BorrowDto;
import ir.mahdi.libra.controller.dto.CreateBorrowDto;
import ir.mahdi.libra.controller.dto.UserDto;
import ir.mahdi.libra.model.Borrow;
import ir.mahdi.libra.repository.BorrowRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultBorrowManagerService implements BorrowManagerService {
    private final BorrowRepository borrowRepository;

    public DefaultBorrowManagerService(BorrowRepository borrowRepository) {
        this.borrowRepository = borrowRepository;
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
        Borrow borrow = new Borrow(
                createBorrowDto.getUserId(),
                createBorrowDto.getBookId(),
                createBorrowDto.getReturnDate()
        );
        borrowRepository.save(borrow);
        return BorrowDto.of(borrow);
    }

    @Override
    public BorrowDto updateBorrow(Long id, CreateBorrowDto createBorrowDto) {
        Borrow borrow = borrowRepository.findById(id);
        borrow.setUserId(createBorrowDto.getUserId());
        borrow.setBookId(createBorrowDto.getBookId());
        borrow.setReturnDate(createBorrowDto.getReturnDate());
        borrowRepository.update(borrow);
        return BorrowDto.of(borrow);
    }

    @Override
    public void deleteBorrow(Long id) {
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
}
