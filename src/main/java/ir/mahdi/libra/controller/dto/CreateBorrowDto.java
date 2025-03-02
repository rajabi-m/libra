package ir.mahdi.libra.controller.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CreateBorrowDto {
    @Positive(message = "User ID must be positive")
    private long userId;
    @Positive(message = "Book ID must be positive")
    private long bookId;
    @Future(message = "Return date must be in the future")
    private LocalDate returnDate;
}
