package ir.mahdi.libra.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Borrow {
    private final long id;
    private final long userId;
    private final long assetId;
    private final String borrowDate;
    private final String returnDate;

    public Borrow(long userId, long assetId, String borrowDate, String returnDate) {
        id = -1;
        this.userId = userId;
        this.assetId = assetId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
