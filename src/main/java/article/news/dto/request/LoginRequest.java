package article.news.dto.request;

import javax.validation.constraints.NotNull;

/**
 * User authentication request class
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class LoginRequest {
    @NotNull
    private String username;

    @NotNull
    private String password;

    public LoginRequest() {
    }

    public LoginRequest(@NotNull String username, @NotNull String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
