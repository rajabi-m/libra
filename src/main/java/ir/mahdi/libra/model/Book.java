package ir.mahdi.libra.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Book extends Asset {
    private String author;
    private int releaseYear;
    private Status status = Status.AVAILABLE;

    public Book(String title, String author, int releaseYear) {
        super(title);
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public Book(long id, String title, String author, int releaseYear) {
        super(id, title);
        this.author = author;
        this.releaseYear = releaseYear;
    }

    public enum Status {
        AVAILABLE,
        BORROWED
    }
}
