package article.news.dto.response;

/**
 * Custom login response
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class LoginResponse {
    private String token;

    private Integer sessionTimeout;

    public LoginResponse(String token, Integer sessionTimeout) {
        this.token = token;
        this.sessionTimeout = sessionTimeout;
    }

    public String getToken() {
        return token;
    }

    public Integer getSessionTimeout() {
        return sessionTimeout;
    }
}
