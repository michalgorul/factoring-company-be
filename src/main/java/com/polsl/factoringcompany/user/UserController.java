package com.polsl.factoringcompany.user;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The User controller.
 * @author Michal Goral
 * @version 1.0
 */
@AllArgsConstructor
@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    /**
     * the bean of user service
     */
    private final UserService userService;

    /**
     * Gets users.
     *
     * @return all users in database
     */
    @GetMapping
    public List<UserEntity> getUsers() {
        return this.userService.getUsers();
    }

    /**
     * Gets current user logged in JWT token.
     *
     * @return the current user logged in JWT token.
     */
    @GetMapping(path = "/current")
    public UserEntity getCurrentUser() {
        return this.userService.getCurrentUser();
    }

    /**
     * Gets user.
     *
     * @param id the id of the desired user
     * @return the user entity
     */
    @GetMapping(path = "/{id}")
    public UserEntity getUser(@PathVariable Long id) {
        return this.userService.getUser(id);
    }

    /**
     * Add new user entity.
     *
     * @param userEntity the user entity
     * @return the user entity
     */
    @PostMapping
    public UserEntity addUser(@RequestBody UserEntity userEntity) {
        return this.userService.addUser(userEntity);
    }

    /**
     * Update user entity.
     *
     * @param id         the id of user to be updated
     * @param userEntity the user entity
     * @return the user entity
     */
    @PutMapping("/{id}")
    public UserEntity updateUser(@PathVariable Long id, @RequestBody UserEntity userEntity) {
        return this.userService.updateUser(id, userEntity);
    }

    /**
     * Update current user logged in JWT token.
     *
     * @param userRequestDto the user logged in JWT token.
     * @return the user entity
     */
    @PutMapping("/current")
    public UserEntity updateCurrentUser(@RequestBody UserRequestDto userRequestDto) {
        return this.userService.updateCurrentUser(userRequestDto);
    }

    /**
     * Delete user.
     *
     * @param id the id of user to be deleted
     */
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        this.userService.deleteUser(id);
    }
}
