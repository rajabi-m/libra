package ir.mahdi.libra.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Data
@RequiredArgsConstructor
public class User {
    @Setter(AccessLevel.NONE)
    private final Long id;
    private String username;
}
