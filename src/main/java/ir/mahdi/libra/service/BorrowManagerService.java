package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.*;

import java.util.List;

public interface BorrowManagerService {
    List<BorrowDto> getAllBorrows();

    BorrowDto getBorrowById(Long id);

    BorrowDto createBorrow(CreateBorrowDto createBorrowDto);

    BorrowDto updateBorrow(Long id, UpdateBookDto updateBookDto);

    void deleteBorrow(Long id);

    List<BookDto> getUserBorrows(Long userId);

    List<UserDto> getAssetBorrowers(Long bookId);
}
