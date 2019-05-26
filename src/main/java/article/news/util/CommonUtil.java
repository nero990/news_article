package article.news.util;

import javax.persistence.EntityNotFoundException;
/**
 * Defines static operation methods
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class CommonUtil {
    public static <T> void throwEntityNotFound(boolean shouldThrow, T entity) {
        if(shouldThrow && entity == null) throw new EntityNotFoundException("Resource Not Found");
    }
}