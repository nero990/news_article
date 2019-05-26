package article.news.exception;

/**
 * Handles bad request and produces 400 HTTP response code
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class ErrorException extends RuntimeException{

    private String code;

    public ErrorException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
