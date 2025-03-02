package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.IdNotFoundException;
import ir.mahdi.libra.exception.UsernameIsNotUniqueException;
import ir.mahdi.libra.exception.UsernameNotFoundException;
import ir.mahdi.libra.model.User;
import ir.mahdi.libra.utils.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class DefaultUserRepository implements UserRepository {
    private final HashMap<Long, User> users = new HashMap<>();

    @Override
    public void save(User user) {
        if (user == null) {
            throw new NullPointerException("User cannot be null");
        }

        if (isUsernameNotUnique(user, user.getUsername())) {
            throw new UsernameIsNotUniqueException("Username is already taken");
        }

        long userId = user.getId() == -1 ? users.size() + 1 : user.getId();
        ReflectionUtils.changeId(user, userId);
        users.put(userId, user);
    }

    @Override
    public User findById(long id) {
        if (!users.containsKey(id)) {
            throw new IdNotFoundException("Cannot find user with id: " + id);
        }
        return users.get(id);
    }

    @Override
    public User findByUsername(String username) {
        User user = findByUsernameNullable(username);
        if (user == null) {
            throw new UsernameNotFoundException("Cannot find user with username: " + username);
        }
        return user;
    }

    private User findByUsernameNullable(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void deleteById(long id) {
        if (!users.containsKey(id)) {
            throw new IdNotFoundException("Cannot find user with id: " + id);
        }
        users.remove(id);
    }

    @Override
    public List<User> findAll() {
        return users.values().stream().toList();
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new NullPointerException("User cannot be null");
        }
        if (!users.containsKey(user.getId())) {
            throw new IdNotFoundException("Cannot find user with id: " + user.getId());
        }
        if (isUsernameNotUnique(user, user.getUsername())) {
            throw new UsernameIsNotUniqueException("Username is already taken");
        }

        users.put(user.getId(), user);
    }

    private boolean isUsernameNotUnique(User user, String username) {
        User userWithSameUsername = findByUsernameNullable(username);
        return userWithSameUsername != null && userWithSameUsername.getId() != user.getId();
    }
}
