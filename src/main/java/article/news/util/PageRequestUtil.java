package article.news.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import javax.servlet.http.HttpServletRequest;

/**
 * Returns a PageRequest object to get paginated response
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class PageRequestUtil {

    public static PageRequest getPageRequest(HttpServletRequest request) {
        return PageRequest.of(getPage(request), getPaginateSize(request), Sort.Direction.DESC, "createdAt");
    }

    private static int getPage(HttpServletRequest request){
        //Subtract one from the value because PageRequest.of() first parameter starts from 0 index
        return Integer.valueOf(request.getParameter("page") != null ? request.getParameter("page") : "1") - 1;
    }

    private static int getPaginateSize(HttpServletRequest request) {
        return Integer.valueOf(request.getParameter("pageSize") != null ? request.getParameter("pageSize") : "10");
    }
}