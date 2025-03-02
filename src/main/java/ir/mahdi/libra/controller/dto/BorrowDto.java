package ir.mahdi.libra.controller.dto;

import ir.mahdi.libra.model.Borrow;
import lombok.Data;

@Data
public class BorrowDto {
    private long id;
    private long userId;
    private long bookId; // TODO: When database is added to the project, change this to Something better that supports inheritance
    private String borrowDate;
    private String returnDate;

    public static BorrowDto of(Borrow borrow) {
        BorrowDto borrowDto = new BorrowDto();
        borrowDto.setId(borrow.getId());
        borrowDto.setUserId(borrow.getUserId());
        borrowDto.setBookId(borrow.getBookId());
        borrowDto.setBorrowDate(borrow.getBorrowDate().toString());
        borrowDto.setReturnDate(borrow.getReturnDate().toString());
        return borrowDto;
    }
}
