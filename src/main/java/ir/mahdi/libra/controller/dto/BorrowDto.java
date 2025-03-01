package ir.mahdi.libra.controller.dto;

import ir.mahdi.libra.model.Borrow;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class BorrowDto {
    private long id;
    private long userId;
    private long assetId; // TODO: When database is added to the project, change this to Something better that supports inheritance
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date borrowDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;

    public static BorrowDto of(Borrow borrow) {
        BorrowDto borrowDto = new BorrowDto();
        borrowDto.setId(borrow.getId());
        borrowDto.setUserId(borrow.getUserId());
        borrowDto.setAssetId(borrow.getAssetId());
        borrowDto.setBorrowDate(borrow.getBorrowDate());
        borrowDto.setReturnDate(borrow.getReturnDate());
        return borrowDto;
    }
}
