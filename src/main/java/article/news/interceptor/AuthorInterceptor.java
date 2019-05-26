package article.news.interceptor;

import article.news.constant.ErrorCode;
import article.news.exception.ForbiddenException;
import article.news.util.RequestUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Ensure logged in user has a role of author
 *
 * @author Nero Okiewhru
 * @since 2019-05-25
 */
@Component
public class AuthorInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(RequestUtil.getAuthor() == null)
            throw new ForbiddenException(ErrorCode.ACCESS_DENIED, "You don't have permission to access this resource.");

        return true;
    }
}
