package ir.mahdi.libra.model;

import jakarta.persistence.*;
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

    @ManyToOne
    @JoinColumn(name = "borrower_id")
    private User borrower;

    @OneToMany(mappedBy = "asset", fetch = FetchType.LAZY)
    private List<BorrowHistory> borrowHistory;
}
