package ir.mahdi.libra.controller.dto;

import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class CreateBorrowDto {
    @Positive(message = "User ID must be positive")
    private long userId;
    @Positive(message = "Asset ID must be positive")
    private long assetId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date returnDate;
}
