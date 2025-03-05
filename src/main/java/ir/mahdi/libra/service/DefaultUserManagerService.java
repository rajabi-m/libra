package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BorrowHistoryDto;
import ir.mahdi.libra.controller.dto.CreateUserDto;
import ir.mahdi.libra.controller.dto.UserDto;
import ir.mahdi.libra.exception.NotFoundException;
import ir.mahdi.libra.model.BorrowHistory;
import ir.mahdi.libra.model.User;
import ir.mahdi.libra.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class DefaultUserManagerService implements UserManagerService {
    private final UserRepository userRepository;
    private final Environment environment;

    public DefaultUserManagerService(UserRepository userRepository, Environment environment) {
        this.userRepository = userRepository;
        this.environment = environment;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().parallelStream().map(UserDto::of).toList();
    }

    @Override
    public UserDto createUser(CreateUserDto createUserDto) {
        User user = new User(createUserDto.getUsername());
        userRepository.save(user);
        return UserDto.of(user);
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(UserDto::of).orElse(null);
    }

    @Override
    public UserDto updateUser(Long id, CreateUserDto createUserDto) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Cannot find user with id: " + id);
        }
        user.get().setUsername(createUserDto.getUsername());
        userRepository.save(user.get());
        return UserDto.of(user.get());
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new NotFoundException("Cannot find user with username: " + username);
        }
        return UserDto.of(user.get());
    }

    @Override
    public List<BorrowHistoryDto> getBorrowHistory(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new NotFoundException("Cannot find user with id: " + id);
        }

        List<BorrowHistory> borrowHistory = user.get().getBorrowHistory();
        return borrowHistory.stream().map(BorrowHistoryDto::of).toList();
    }

    @PostConstruct
    public void init() {
        if (!Objects.equals(environment.getProperty("application.debug"), "true")) {
            return;
        }

        createUser(new CreateUserDto("admin"));
        createUser(new CreateUserDto("user"));
        createUser(new CreateUserDto("test"));
        createUser(new CreateUserDto("test2"));
    }
}
