package article.news.interceptor;

import article.news.constant.ErrorCode;
import article.news.exception.UnAuthorizedException;
import article.news.service.JwtService;
import article.news.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {
    private final SessionService sessionService;

    @Autowired
    public AuthInterceptor(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        validateToken(request.getHeader("Authorization"));
        return true;
    }

    private void validateToken(String authorization) {
        final String TOKEN_PREFIX = "Bearer";

        if(authorization == null) throw new UnAuthorizedException(ErrorCode.MISSING_AUTHORIZATION, "Missing Authorization header");
        String[] splitToken = authorization.split(" ");

        if(!TOKEN_PREFIX.equals(splitToken[0])) throw new UnAuthorizedException(ErrorCode.INVALID_AUTHORIZATION, "Authorization header not valid");
        if(StringUtils.isEmpty(splitToken[1])) throw new UnAuthorizedException(ErrorCode.INVALID_AUTHORIZATION, "Authorization header not valid");

        try{
            sessionService.sessionUpdate(JwtService.decode(splitToken[1].trim()));
        } catch (Exception exception) {
            exception.printStackTrace();
            throw new UnAuthorizedException(ErrorCode.EXPIRED_SESSION, exception.getMessage());
        }
    }
}
