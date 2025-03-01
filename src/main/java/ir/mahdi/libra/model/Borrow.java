package ir.mahdi.libra.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
public class Borrow {
    private final long id;
    private final long userId;
    private final long assetId;
    private final Date borrowDate;
    private final Date returnDate;

    public Borrow(long userId, long assetId, Date borrowDate, Date returnDate) {
        id = -1;
        this.userId = userId;
        this.assetId = assetId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
}
