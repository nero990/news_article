package article.news.dto.response;

/**
 * Custom login response
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class LoginResponse {
    private String token;

    public LoginResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
