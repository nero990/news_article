package article.news.exception;

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
