package article.news.controller.author;

import article.news.dto.request.RegisterRequest;
import article.news.model.User;
import article.news.service.dao.UserService;
import article.news.shared.Author;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Manages Author sign up and listing
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Api(tags = {"Authors"})
@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final UserService userService;

    @Autowired
    public AuthorController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "List of all available authors", response = Author.class, responseContainer = "Page")
    @GetMapping
    public ResponseEntity<Page<Author>> getAuthors(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getAuthors(request));
    }

    @ApiOperation(value = "Get a single author", response = Author.class)
    @GetMapping("/{username}")
    public ResponseEntity<Author> getAuthor(@PathVariable String username) {
        return ResponseEntity.ok(userService.getAuthor(username));
    }

    @ApiOperation(value = "Author sign-up", response = User.class)
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerAuthor(registerRequest));
    }
}
