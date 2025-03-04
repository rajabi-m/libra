package ir.mahdi.libra.controller;

import ir.mahdi.libra.controller.dto.BorrowHistoryDto;
import ir.mahdi.libra.controller.dto.CreateUserDto;
import ir.mahdi.libra.controller.dto.UserDto;
import ir.mahdi.libra.service.UserManagerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserManagerService userManagerService;

    public UserController(UserManagerService userManagerService) {
        this.userManagerService = userManagerService;
    }

    @GetMapping("/")
    public List<UserDto> getUsers() {
        return userManagerService.getAllUsers();
    }

    @PostMapping("/")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody CreateUserDto createUserDto) {
        UserDto user = userManagerService.createUser(createUserDto);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable Long id) {
        UserDto userDto = userManagerService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getUserByUsername(@PathVariable String username) {
        UserDto userDto = userManagerService.getUserByUsername(username);
        return ResponseEntity.ok(userDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Long id, @Valid @RequestBody CreateUserDto createUserDto) {
        UserDto userDto = userManagerService.updateUser(id, createUserDto);
        return ResponseEntity.ok(userDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userManagerService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}/borrow-history")
    public List<BorrowHistoryDto> getBorrowHistory(@PathVariable Long id) {
        return userManagerService.getBorrowHistory(id);
    }
}
