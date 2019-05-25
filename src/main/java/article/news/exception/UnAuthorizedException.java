package article.news.exception;

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
