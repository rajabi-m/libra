package ir.mahdi.libra.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity
public class Magazine extends BorrowableAsset {
    private String publisher;
    private int issueNumber;
}
