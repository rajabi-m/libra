package ir.mahdi.libra.model;

import jakarta.persistence.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@NoArgsConstructor
public class Book extends BorrowableAsset {
    private String author;
    private int releaseYear;

    public Book(String title, String author, int releaseYear) {
        setTitle(title);
        setAuthor(author);
        setReleaseYear(releaseYear);
    }
}
