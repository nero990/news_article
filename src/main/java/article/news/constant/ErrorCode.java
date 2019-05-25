package article.news.constant;
/**
 * Error code listing
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public interface ErrorCode {
    String RESOURCE_NOT_FOUND = "RESOURCE_NOT_FOUND";
    String DUPLICATE_ENTRY = "DUPLICATE_ENTRY";
    String INVALID_ENTRY = "INVALID_ENTRY";
    String BAD_CREDENTIALS = "BAD_CREDENTIALS";
    String MISSING_AUTHORIZATION = "MISSING_AUTHORIZATION";
    String INVALID_AUTHORIZATION = "INVALID_AUTHORIZATION";
    String UNKNOWN_ERROR = "UNKNOWN_ERROR";
    String EXPIRED_SESSION = "EXPIRED_SESSION";
}
