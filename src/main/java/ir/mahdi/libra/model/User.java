package ir.mahdi.libra.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private final long id;
    private String username;

    public User(String username) {
        id = -1;
        this.username = username;
    }
}
