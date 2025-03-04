package ir.mahdi.libra.model;

import jakarta.persistence.*;
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
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "asset_id")
    private BorrowableAsset asset;

    public BorrowHistory(LocalDate borrowDate, LocalDate returnDate, User user, BorrowableAsset asset) {
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.user = user;
        this.asset = asset;
    }
}
