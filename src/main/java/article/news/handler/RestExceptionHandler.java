package article.news.handler;

import article.news.constant.ErrorCode;
import article.news.dto.response.ErrorWrapper;
import article.news.exception.ErrorException;
import article.news.exception.ForbiddenException;
import article.news.exception.UnAuthorizedException;
import article.news.util.ErrorValidator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

/**
 * Catches Exceptions and returns an custom response
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorWrapper> handleException(EntityNotFoundException e) {
        return new ResponseEntity<>(new ErrorWrapper(ErrorCode.RESOURCE_NOT_FOUND, e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnAuthorizedException.class)
    public ResponseEntity<ErrorWrapper> handleException(UnAuthorizedException e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getCode(), e.getMessage()),HttpStatus.UNAUTHORIZED);
    }
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ErrorWrapper> handleException(ForbiddenException e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getCode(), e.getMessage()),HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorWrapper> handleException(MethodArgumentNotValidException e) {
        return ResponseEntity.unprocessableEntity().body(new ErrorWrapper(
                ErrorCode.VALIDATION_FAILURE,
                "Please supply correct form values",
                ErrorValidator.errors(e)));
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorWrapper> handleException(ErrorException e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
    }
}