package gamebook.gamebook.interceptor;

import gamebook.gamebook.web.SessionConst;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;


public class UnLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(false);

        if (session == null) {
            return true;
        } else if (session.getAttribute(SessionConst.MEMBER_ID) == null) {
            return true;
        }

        response.sendRedirect("/");
        return false;

    }
}
