package net.work100.training.stage2.iot.admin.web.controller;

import net.work100.training.stage2.iot.admin.commons.context.SpringContext;
import net.work100.training.stage2.iot.admin.entity.User;
import net.work100.training.stage2.iot.admin.service.UserService;
//import net.work100.training.stage2.iot.admin.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-example.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-13 13:28
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-13   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class LoginController extends HttpServlet {

    private SpringContext context = new SpringContext();

//    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) context.getBean("userService");
        User user = userService.login("admin", "admin");
        System.out.println("--------------doGet test(begin)-----------------");
        System.out.println(user);
        System.out.println("--------------doGet test(end)-----------------");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = (UserService) context.getBean("userService");
        String loginId = req.getParameter("loginId");
        String loginPwd = req.getParameter("loginPwd");

        User user = userService.login(loginId, loginPwd);

        // 登录成功
        if (user != null) {
            // 重定向到首页
            resp.sendRedirect("/main.jsp");
        }
        // 登录失败
        else {
            // 跳转回登录页
            req.setAttribute("message", "登录ID或登录密码错误");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
