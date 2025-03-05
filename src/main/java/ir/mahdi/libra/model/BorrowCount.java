package ir.mahdi.libra.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Data
@Table(name = "borrow_counts_view")
public class BorrowCount {
    @Id
    @Column(name = "id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private BorrowableAsset asset;

    @Column(name = "count")
    private Long count;
}
