package ir.mahdi.libra.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Borrow {
    private final long id;
    private long userId;
    private long bookId;
    private final LocalDate borrowDate;
    private LocalDate returnDate;

    public Borrow(long userId, long bookId, LocalDate returnDate) {
        id = -1;
        borrowDate = null;
        this.userId = userId;
        this.bookId = bookId;
        this.returnDate = returnDate;
    }
}
