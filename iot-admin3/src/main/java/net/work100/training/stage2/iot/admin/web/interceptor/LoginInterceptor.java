package net.work100.training.stage2.iot.admin.web.interceptor;

import net.work100.training.stage2.iot.admin.commons.constant.ConstantUtils;
import net.work100.training.stage2.iot.admin.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: LoginInterceptor</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-mvc.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-20 16:22
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-20   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("LoginInterceptor");

        User user = (User) request.getSession().getAttribute(ConstantUtils.SESSION_USER);

        // 未登录
        if (user == null) {
            response.sendRedirect("/login");
        }

        // 为 true 时放行，进入 postHandle
        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
