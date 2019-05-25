package article.news.controller;

import article.news.dto.request.user.CreateUserRequest;
import article.news.dto.request.user.UpdateUserRequest;
import article.news.dto.response.DeleteResponse;
import article.news.dto.response.ResponseBuilder;
import article.news.model.User;
import article.news.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserController -> Manages CRUD operations for user
 *
 * @author Nero Okiewhru
 * @since 2019-05-24
 */

@RestController
@Api(tags = {"User Resource"})
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "list of available users", response = User.class, responseContainer = "List")
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @ApiOperation(value = "Create a new user", response = User.class)
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest createUserRequest) {
        User user = userService.createUser(createUserRequest);
        return ResponseBuilder.created(user, user.getId());
    }

    @ApiOperation(value = "Get one user by id", response = User.class)
    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUser(id, true));
    }

    @ApiOperation(value = "Update a user", response = User.class)
    @PutMapping("{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest updateUserRequest) {
        return ResponseEntity.ok(userService.updateUser(id, updateUserRequest));
    }

    @ApiOperation(value = "Delete a user", response = DeleteResponse.class)
    @DeleteMapping("{id}")
    public DeleteResponse DeleteUser(@PathVariable Long id) {
        return new DeleteResponse(userService.deleteUser(id));
    }
}
