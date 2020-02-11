package net.work100.training.stage2.hello.maven.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>Title: HelloServlet</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-01-15 12:34
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-01-15   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/index.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
