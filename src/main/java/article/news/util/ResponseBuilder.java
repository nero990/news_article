package article.news.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Util for building HTTP response
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class ResponseBuilder {
    public static <T> ResponseEntity<T> created(T entity, Long id) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri())
                .body(entity);
    }
}