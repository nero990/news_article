package article.news.util;

import org.springframework.util.StringUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * MethodArgumentNotValidException error formatter
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class ErrorValidator {
    public static Map<String, Object> errors (MethodArgumentNotValidException methodArgumentNotValid){
        Map <String, Object> errors = new HashMap<>();
        for(FieldError fieldError : methodArgumentNotValid.getBindingResult().getFieldErrors()) {
            String fieldName = fieldError.getField();
            if(fieldError.getField().contains("data.")){
                fieldName = fieldName.replace("data.","");
            }
            errors.put(fieldName, StringUtils.capitalize(fieldName) + " " + fieldError.getDefaultMessage());
        }
        return errors.keySet().isEmpty() ? null : errors;
    }

}