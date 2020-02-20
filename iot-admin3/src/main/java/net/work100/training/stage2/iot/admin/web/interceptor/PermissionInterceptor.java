package net.work100.training.stage2.iot.admin.web.interceptor;

import net.work100.training.stage2.iot.admin.commons.constant.ConstantUtils;
import net.work100.training.stage2.iot.admin.entity.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: PermissionInterceptor</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-mvc.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-20 21:16
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-20   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class PermissionInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("PermissionInterceptor");

        return true;
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if ("login".equals(modelAndView.getViewName())) {
            User user = (User) request.getSession().getAttribute(ConstantUtils.SESSION_USER);
            if (user != null) {
                response.sendRedirect("/main");
            }
        }
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
