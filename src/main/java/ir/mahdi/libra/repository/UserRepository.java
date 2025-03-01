package ir.mahdi.libra.repository;

import ir.mahdi.libra.model.User;

import java.util.List;

public interface UserRepository {
    void save(User user);

    User findById(long id);

    User findByUsername(String username);

    void deleteById(long id);

    List<User> findAll();

    void update(User user);
}
