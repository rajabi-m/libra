package ir.mahdi.libra.controller.dto;

import ir.mahdi.libra.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @NotNull(message = "Release year is required")
    @Positive(message = "Release year must be positive")
    private int releaseYear;

    public static CreateBookDto of(Book book) {
        return new CreateBookDto(book.getTitle(), book.getAuthor(), book.getReleaseYear());
    }
}
