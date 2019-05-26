package article.news.dto.response;

/**
 * Custom response for logout operation
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class LoggedOutResponse {
    private Boolean loggedOut;

    public LoggedOutResponse(Boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public Boolean getLoggedOut() {
        return loggedOut;
    }
}
