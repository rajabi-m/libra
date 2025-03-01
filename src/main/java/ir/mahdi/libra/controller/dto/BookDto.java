package ir.mahdi.libra.controller.dto;

import ir.mahdi.libra.model.Book;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BookDto {
    @Positive(message = "Id must be positive")
    private Long id;
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @Positive(message = "Release year must be positive")
    private int releaseYear;

    public static BookDto of(Book book){
        BookDto bookDto = new BookDto();
        bookDto.setId(book.getId());
        bookDto.setTitle(book.getTitle());
        bookDto.setAuthor(book.getAuthor());
        bookDto.setReleaseYear(book.getReleaseYear());
        return bookDto;
    }
}
