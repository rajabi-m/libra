package ir.mahdi.libra.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateBookDto {
    @NotBlank(message = "Title is required")
    private String title;
    @NotBlank(message = "Author is required")
    private String author;
    @NotNull(message = "Release year is required")
    @Positive(message = "Release year must be positive")
    private int releaseYear;
}
