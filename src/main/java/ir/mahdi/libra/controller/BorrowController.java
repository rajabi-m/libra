package ir.mahdi.libra.controller;

import ir.mahdi.libra.controller.dto.*;
import ir.mahdi.libra.service.BorrowManagerService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrows")
public class BorrowController {
    private final BorrowManagerService borrowManagerService;

    public BorrowController(BorrowManagerService borrowManagerService) {
        this.borrowManagerService = borrowManagerService;
    }

    @GetMapping("/")
    public List<BorrowDto> getBorrows() {
        return borrowManagerService.getAllBorrows();
    }

    @GetMapping("/{id}")
    public BorrowDto getBorrow(@PathVariable Long id) {
        return borrowManagerService.getBorrowById(id);
    }

    @PostMapping("/")
    public BorrowDto createBorrow(@Valid @RequestBody CreateBorrowDto createBorrowDto) {
        return borrowManagerService.createBorrow(createBorrowDto);
    }

    @PutMapping("/{id}")
    public BorrowDto updateBorrow(@PathVariable Long id, @Valid @RequestBody UpdateBookDto updateBookDto) {
        return borrowManagerService.updateBorrow(id, updateBookDto);
    }

    @DeleteMapping("/{id}")
    public void deleteBorrow(@PathVariable Long id) {
        borrowManagerService.deleteBorrow(id);
    }

    @GetMapping("/user/{userId}")
    public List<BookDto> getUserBorrows(@PathVariable Long userId) {
        return borrowManagerService.getUserBorrows(userId);
    }

    @GetMapping("/book/{bookId}")
    public List<UserDto> getAssetBorrowers(@PathVariable Long bookId) {
        return borrowManagerService.getAssetBorrowers(bookId);
    }
}
