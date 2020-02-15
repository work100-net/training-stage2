package net.work100.training.stage2.iot.admin.web.controller;

import net.work100.training.stage2.iot.admin.commons.context.SpringContext;
import net.work100.training.stage2.iot.admin.commons.utils.CookieUtils;
import net.work100.training.stage2.iot.admin.entity.User;
import net.work100.training.stage2.iot.admin.service.UserService;
import net.work100.training.stage2.iot.admin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-web.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-13 13:28
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-13   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class LoginController extends HttpServlet {

    private final static String COOKIE_LOGIN_ID = "loginId";
    private final static String COOKIE_REMEMBER = "remember";

    private UserService userService1 = SpringContext.getBean(UserServiceImpl.class);
    private UserService userService2 = SpringContext.getBean(UserServiceImpl.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // spring-context.xml 中 scope="singleton" 时：true
        // spring-context.xml 中 scope="prototype" 时：false
//        System.out.println(userService1 == userService2);

        boolean remember = "on".equals(CookieUtils.getCookieValue(req, COOKIE_REMEMBER));
        if (remember) {
            String loginId = CookieUtils.getCookieValue(req, COOKIE_LOGIN_ID);
            req.setAttribute("remember", remember);
            req.setAttribute("loginId", loginId);
        }
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginId = req.getParameter("loginId");
        String loginPwd = req.getParameter("loginPwd");
        boolean remember = "on".equals(req.getParameter("remember"));

        System.out.println(remember);
        User user = userService1.login(loginId, loginPwd);

        // 登录成功
        if (user != null) {
            if (remember) {
                // Cookie 存储一周
                CookieUtils.setCookie(req, resp, COOKIE_REMEMBER, "on", 7 * 24 * 60 * 60);
                CookieUtils.setCookie(req, resp, COOKIE_LOGIN_ID, loginId, 7 * 24 * 60 * 60);
            } else {
                // 删除 Cookie
                CookieUtils.deleteCookie(req, resp, COOKIE_REMEMBER);
                CookieUtils.deleteCookie(req, resp, COOKIE_LOGIN_ID);
            }
            // 重定向到首页
            resp.sendRedirect("/main.jsp");
        }
        // 登录失败
        else {
            // 跳转回登录页
            req.setAttribute("message", "登录ID或登录密码错误");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
