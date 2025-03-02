package ir.mahdi.libra.controller.dto;

import jakarta.validation.constraints.Future;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UpdateBookDto {
    @Future(message = "Return date must be in the future")
    private LocalDate returnDate;
}
