package ir.mahdi.libra.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateUserDto {
    @NotBlank(message = "Username is required")
    private String username;
}
