package ir.mahdi.libra.repository;

import ir.mahdi.libra.exception.NotFoundException;
import ir.mahdi.libra.exception.NotUniqueException;
import ir.mahdi.libra.model.User;
import ir.mahdi.libra.utils.ReflectionUtils;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

@Service
public class DefaultUserRepository implements UserRepository {
    private final HashMap<Long, User> users = new HashMap<>();
    private long idCounter = 0;
    private final Environment environment;

    public DefaultUserRepository(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void save(User user) {
        if (user == null) {
            throw new NullPointerException("User cannot be null");
        }

        if (isUsernameNotUnique(user, user.getUsername())) {
            throw new NotUniqueException("Username is already taken");
        }

        idCounter++;
        long userId = idCounter;
        ReflectionUtils.setId(user, userId, User.class);
        users.put(userId, user);
    }

    @Override
    public User findById(long id) {
        if (!users.containsKey(id)) {
            throw new NotFoundException("Cannot find user with id: " + id);
        }
        return users.get(id);
    }

    @Override
    public User findByUsername(String username) {
        User user = findByUsernameNullable(username);
        if (user == null) {
            throw new NotFoundException("Cannot find user with username: " + username);
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
            throw new NotFoundException("Cannot find user with id: " + id);
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
            throw new NotFoundException("Cannot find user with id: " + user.getId());
        }
        if (isUsernameNotUnique(user, user.getUsername())) {
            throw new NotUniqueException("Username is already taken");
        }

        users.put(user.getId(), user);
    }

    private boolean isUsernameNotUnique(User user, String username) {
        User userWithSameUsername = findByUsernameNullable(username);
        return userWithSameUsername != null && userWithSameUsername.getId() != user.getId();
    }

    @PostConstruct
    public void init() {
        if (!Objects.equals(environment.getProperty("application.debug"), "true")) {
            return;
        }
        User user1 = new User("user1");
        User user2 = new User("user2");
        User user3 = new User("user3");
        User user4 = new User("user4");
        User user5 = new User("user5");

        save(user1);
        save(user2);
        save(user3);
        save(user4);
        save(user5);
    }
}
