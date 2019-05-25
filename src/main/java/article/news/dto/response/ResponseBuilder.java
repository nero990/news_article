package article.news.dto.response;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class ResponseBuilder {
    public static <T> ResponseEntity<T> created(T entity, Long id) {
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(id).toUri())
                .body(entity);
    }
}