package article.news.controller;

import article.news.dto.request.RegisterRequest;
import article.news.model.User;
import article.news.service.dao.UserService;
import article.news.shared.Writer;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * Manages writer sign up and listing
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Api(tags = {"Writers"})
@RestController
@RequestMapping("/writers")
public class WriterController {
    private final UserService userService;

    @Autowired
    public WriterController(UserService userService) {
        this.userService = userService;
    }

    @ApiOperation(value = "List of all available writers", response = Writer.class, responseContainer = "Page")

    @GetMapping
    public ResponseEntity<Page<Writer>> getWriters(HttpServletRequest request) {
        return ResponseEntity.ok(userService.getWriters(request));
    }

    @ApiOperation(value = "Writer sign-up", response = User.class)
    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(userService.registerWriter(registerRequest));
    }
}
