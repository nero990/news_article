package article.news.exception;

/**
 * Handles UnAuthorized request and produces 401 HTTP response code
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class UnAuthorizedException extends RuntimeException {
    private String code;

    public UnAuthorizedException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
