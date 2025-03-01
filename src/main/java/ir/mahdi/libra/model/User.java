package ir.mahdi.libra.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@Data
@AllArgsConstructor
public class User {
    @Setter(AccessLevel.NONE)
    private final long id;
    private String username;

    public User(String username) {
        id = -1;
        this.username = username;
    }
}
