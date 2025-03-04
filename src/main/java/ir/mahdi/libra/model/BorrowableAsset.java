package ir.mahdi.libra.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public abstract class BorrowableAsset extends Asset {
    private LocalDate borrowDate;
    private LocalDate returnDate;

    @OneToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @OneToMany
    private List<BorrowHistory> borrowHistory;
}
