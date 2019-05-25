package article.news.handler;

import article.news.constant.ErrorCode;
import article.news.dto.response.ErrorWrapper;
import article.news.exception.ErrorException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorWrapper> handleException(EntityNotFoundException e) {
        return new ResponseEntity<>(new ErrorWrapper(ErrorCode.RESOURCE_NOT_FOUND, e.getMessage()),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ErrorException.class)
    public ResponseEntity<ErrorWrapper> handleException(ErrorException e) {
        return new ResponseEntity<>(new ErrorWrapper(e.getCode(), e.getMessage()),HttpStatus.BAD_REQUEST);
    }
}