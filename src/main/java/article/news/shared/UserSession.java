package article.news.shared;


import article.news.model.Role;
import article.news.model.Session;
import article.news.model.User;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * Custom User object for JWT
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Getter
@Setter
public class UserSession {
    @NotNull
    private Long id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String username;

    @NotNull
    private String email;

    @NotNull
    private Session session;

    @NotNull
    private Role role;

    public UserSession() {
    }

    public UserSession(User user, Session session) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.session = session;
        this.role = user.getRole();
    }
}