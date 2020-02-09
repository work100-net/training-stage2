package net.work100.training.stage2.login.demo.web.controller;

import net.work100.training.stage2.login.demo.service.UserService;
import net.work100.training.stage2.login.demo.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: LoginController</p>
 * <p>Description: 登录控制器</p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-test.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-09 11:36
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-09   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class LoginController extends HttpServlet {

    private UserService userService = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String loginId = req.getParameter("loginId");
        String loginPwd = req.getParameter("loginPwd");

        boolean success = userService.login(loginId, loginPwd);

        // 登录失败
        if (!success) {
            req.getRequestDispatcher("/fail.jsp").forward(req, resp);
        }
        // 登录成功
        else {
            req.getRequestDispatcher("/success.jsp").forward(req, resp);
        }
    }
}
