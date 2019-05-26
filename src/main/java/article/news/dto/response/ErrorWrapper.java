package article.news.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;

import java.util.Map;
/**
 * Custom error response
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorWrapper{
    private String errorCode;
    private String message;
    private Map<String, Object> errors;

    public ErrorWrapper(String errorCode, String message) {
        this(errorCode, message, null);
    }

    public ErrorWrapper(String errorCode, String message, Map<String, Object> errors) {
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }
}
