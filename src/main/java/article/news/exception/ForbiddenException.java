package article.news.exception;

/**
 * Handles forbidden request and produces 403 HTTP response code
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class ForbiddenException extends RuntimeException {
    private String code;

    public ForbiddenException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
