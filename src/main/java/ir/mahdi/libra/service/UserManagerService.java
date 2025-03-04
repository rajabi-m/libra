package ir.mahdi.libra.service;

import ir.mahdi.libra.controller.dto.BorrowHistoryDto;
import ir.mahdi.libra.controller.dto.CreateUserDto;
import ir.mahdi.libra.controller.dto.UserDto;
import jakarta.validation.Valid;

import java.util.List;

public interface UserManagerService {
    List<UserDto> getAllUsers();

    UserDto createUser(CreateUserDto createUserDto);

    UserDto getUserById(Long id);

    UserDto updateUser(Long id, @Valid CreateUserDto createUserDto);

    void deleteUser(Long id);

    UserDto getUserByUsername(String username);

    List<BorrowHistoryDto> getBorrowHistory(Long id);
}
