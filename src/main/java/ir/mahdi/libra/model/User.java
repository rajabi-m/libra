package ir.mahdi.libra.model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.NONE)
    private Long id;
    private String username;

    @OneToOne(mappedBy = "borrower")
    private BorrowableAsset borrowedAsset;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<BorrowHistory> borrowHistory;

    public User(String username) {
        this.username = username;
    }
}
