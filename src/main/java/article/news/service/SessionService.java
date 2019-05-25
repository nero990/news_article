package article.news.service;

import article.news.constant.ErrorCode;
import article.news.dto.request.LoginRequest;
import article.news.dto.response.LoginResponse;
import article.news.exception.ErrorException;
import article.news.model.Session;
import article.news.model.User;
import article.news.repository.SessionRepository;
import article.news.repository.UserRepository;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Handles user session (Login and logout)
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */

@Service
public class SessionService {

    private final SessionRepository sessionRepository;
    private final UserRepository userRepository;

    @Autowired
    public SessionService(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    public LoginResponse authenticate(LoginRequest loginRequest) {
        User user = userRepository.findByUsernameOrEmail(loginRequest.getUsername(), loginRequest.getUsername());
        if (user == null || !passwordVerify(loginRequest.getPassword(), user.getPassword()))
            throw new ErrorException(ErrorCode.BAD_CREDENTIALS, "Bad login credentials");

        return new LoginResponse(sessionStart(user));
    }

    private Boolean passwordVerify(String password, String passwordHash) {
        try {
            return BCrypt.checkpw(password, passwordHash);
        } catch (Exception e) {
            return false;
        }
    }

    private String sessionStart(User user) {
        return new JwtService<>(sessionRepository.save(new Session(user))).encode();
    }

    public void sessionUpdate(Claims claims) throws Exception {
        Long sessionId = Long.valueOf((Integer) claims.get("id"));
        Session session = sessionRepository.findById(sessionId).orElse(null);
        if (session == null) throw new Exception("Session Expired");

        int timeOut = 30; // Session timeOut value in minutes

        if (isExpired(session.getLastActiveAt().getTime() + (1000L * 60 * timeOut))) {
            session.setLoggedOutAt(new Date());
            sessionRepository.save(session);
            throw new Exception("Session Expired");
        }

        session.setLastActiveAt(new Date());
        sessionRepository.save(session);
    }

    private boolean isExpired(Long expirationTime) {
        return expirationTime < new Date().getTime();
    }
}