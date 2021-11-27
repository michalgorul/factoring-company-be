package com.polsl.factoringcompany.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getUsers() {
        return this.userService.getUsers();
    }

    @GetMapping(path = "/current")
    public UserEntity getCurrentUser() {
        return this.userService.getCurrentUser();
    }

    @GetMapping(path = "/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        return this.userService.getUser(id);
    }

    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return this.userService.addUser(userEntity);
    }

    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        return this.userService.updateUser(id, userEntity);
    }

    @PutMapping("/current")
    public UserEntity updateCurrentUser(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.updateCurrentUser(userRequestDto);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }
}
