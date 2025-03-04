package ir.mahdi.libra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@NoArgsConstructor
public class BorrowHistory {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;

    private LocalDate borrowDate;

    private LocalDate returnDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private BorrowableAsset asset;

    public BorrowHistory(LocalDate borrowDate, LocalDate returnDate, User user, BorrowableAsset asset) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.user = user;
        this.asset = asset;
    }
}
