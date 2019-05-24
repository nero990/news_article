package article.news.controller;

import article.news.model.User;
import article.news.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * UserController -> Manages CRUD operations for user
 *
 * @author Nero Okiewhru
 * @since 2019-05-24
 */

@RestController
@Api(description = "User Management", tags = {"Users"})
@RequestMapping("users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "list of available users", response = List.class)
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }
}
