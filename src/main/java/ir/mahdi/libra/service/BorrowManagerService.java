package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BookDto;
import ir.mahdi.libra.controller.dto.BorrowDto;
import ir.mahdi.libra.controller.dto.CreateBorrowDto;
import ir.mahdi.libra.controller.dto.UserDto;

import java.util.List;

public interface BorrowManagerService {
    List<BorrowDto> getAllBorrows();

    BorrowDto getBorrowById(Long id);

    BorrowDto createBorrow(CreateBorrowDto createBorrowDto);

    BorrowDto updateBorrow(Long id, CreateBorrowDto createBorrowDto);

    void deleteBorrow(Long id);

    List<BookDto> getUserBorrows(Long userId);

    List<UserDto> getAssetBorrowers(Long bookId);
}
