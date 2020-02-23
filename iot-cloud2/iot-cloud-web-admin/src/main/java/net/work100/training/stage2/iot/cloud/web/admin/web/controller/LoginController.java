package net.work100.training.stage2.iot.cloud.web.admin.web.controller;

import net.work100.training.stage2.iot.cloud.commons.constant.ConstantUtils;
import net.work100.training.stage2.iot.cloud.domain.User;
import net.work100.training.stage2.iot.cloud.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-mvc-maven-module.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-22 16:15
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-22   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    /**
     * 登录逻辑
     *
     * @param loginId  登录ID
     * @param loginPwd 登录密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String loginId,
                        @RequestParam(required = true) String loginPwd,
                        HttpServletRequest request) {

        User user = userService.login(loginId, loginPwd);

        // 登录成功
        if (user != null) {
            // 将登录信息记入Session
            request.getSession().setAttribute(ConstantUtils.SESSION_USER, user);
            return "redirect:/main";
        }
        // 登录失败
        else {
            return login();
        }
    }

    /**
     * 登录注销
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
