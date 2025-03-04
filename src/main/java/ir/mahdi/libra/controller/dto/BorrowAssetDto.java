package ir.mahdi.libra.controller.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BorrowAssetDto {
    @Positive(message = "User id must be positive")
    private long userId;
    @Future(message = "Return date must be in the future")
    private LocalDate returnDate;
}
