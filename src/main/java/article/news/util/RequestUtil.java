package article.news.util;

import article.news.constant.CommonConstant;
import article.news.model.Session;
import article.news.model.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * Stores and retrieves values in request parameters
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
public class RequestUtil {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static User getAuthor() {
        User user = getSession().getUser();
        if(user != null && user.getRole().getName().equals(CommonConstant.AUTHOR)) {
            return user;
        }
        return null;
    }

    public static void setSession(Session session) {
        getRequest().setAttribute("SESSION", session);
    }

    public static Session getSession() {
        return (Session) getRequest().getAttribute("SESSION");
    }
}