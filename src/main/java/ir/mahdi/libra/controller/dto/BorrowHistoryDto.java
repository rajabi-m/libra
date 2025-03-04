package ir.mahdi.libra.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class BorrowHistoryDto {
    private AssetDto asset;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public static BorrowHistoryDto of(ir.mahdi.libra.model.BorrowHistory borrowHistory) {
        return new BorrowHistoryDto(
                AssetDto.of(borrowHistory.getAsset()),
                borrowHistory.getBorrowDate(),
                borrowHistory.getReturnDate()
        );
    }
}
