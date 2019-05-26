package article.news.dto.response;

public class LoggedOutResponse {
    private Boolean loggedOut;

    public LoggedOutResponse(Boolean loggedOut) {
        this.loggedOut = loggedOut;
    }

    public Boolean getLoggedOut() {
        return loggedOut;
    }
}
