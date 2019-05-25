package article.news.util;

import javax.persistence.EntityNotFoundException;

public class CommonUtil {
    public static <T> void throwEntityNotFound(boolean shouldThrow, T entity) {
        if(shouldThrow && entity == null) throw new EntityNotFoundException("Resource Not Found");
    }
}