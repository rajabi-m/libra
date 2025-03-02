package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.CreateUserDto;
import ir.mahdi.libra.controller.dto.UserDto;
import ir.mahdi.libra.model.User;
import ir.mahdi.libra.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefaultUserManagerService implements UserManagerService {
    private final UserRepository userRepository;

    public DefaultUserManagerService(UserRepository userRepository) {
        this.userRepository = userRepository;
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
        return UserDto.of(userRepository.findById(id));
    }

    @Override
    public UserDto updateUser(Long id, CreateUserDto createUserDto) {
        User user = userRepository.findById(id);
        user.setUsername(createUserDto.getUsername());
        userRepository.update(user);
        return UserDto.of(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
