package article.news.controller;

import article.news.dto.request.LoginRequest;
import article.news.dto.response.LoggedOutResponse;
import article.news.dto.response.LoginResponse;
import article.news.service.SessionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Api(tags = {"Authentication"})
@RestController
@RequestMapping("/session")
public class SessionController {

    private final SessionService sessionService;

    @Autowired
    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @ApiOperation(value = "Create user session", response = LoginResponse.class)
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(sessionService.authenticate(loginRequest));
    }

    @ApiOperation(value = "Destroy user session", response = LoggedOutResponse.class)
    @GetMapping("/logout")
    public ResponseEntity<LoggedOutResponse> logout() {
        return ResponseEntity.ok(sessionService.destroySession());
    }

}
