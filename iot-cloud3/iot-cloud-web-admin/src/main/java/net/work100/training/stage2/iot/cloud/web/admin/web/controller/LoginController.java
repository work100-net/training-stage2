package net.work100.training.stage2.iot.cloud.web.admin.web.controller;

import net.work100.training.stage2.iot.cloud.commons.constant.ConstantUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.CookieUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.DateTimeUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.IpUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.domain.AuthManagerProfile;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerProfileService;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
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
    private AuthManagerService authManagerService;

    @Autowired
    private AuthManagerProfileService authManagerProfileService;

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login(HttpServletRequest request, Model model) {
        boolean isRemember = "on".equals(CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_MANAGER_REMEMBER));
        if (isRemember) {
            String userName = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_MANAGER_USERNAME);
            model.addAttribute("remember", isRemember);
            model.addAttribute("userName", userName);
        }
        return "login";
    }

    /**
     * 登录逻辑
     *
     * @param userName 用户名
     * @param password 登录密码
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true) String userName,
                        @RequestParam(required = true) String password,
                        @RequestParam(required = false) String remember,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {


        boolean isRemember = "on".equalsIgnoreCase(remember);

        AuthManager authManager = authManagerService.login(userName, password);

        // 登录成功
        if (authManager != null) {
            // 处理记住我功能(记住用户名)
            if (isRemember) {
                // Cookie 存储一周
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_MANAGER_REMEMBER, "on", 7 * 24 * 60 * 60);
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_MANAGER_USERNAME, userName, 7 * 24 * 60 * 60);
            } else {
                // 删除 Cookie
                CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_MANAGER_REMEMBER);
                CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_MANAGER_USERNAME);
            }
            // 将登录信息记入Session
            request.getSession().setAttribute(ConstantUtils.SESSION_MANAGER, authManager);

            // ---------------- 记录登录信息(begin) ----------------
            List<AuthManagerProfile> profiles = new ArrayList<>();
            AuthManagerProfile profileLoginTime = new AuthManagerProfile();
            profileLoginTime.setUserKey(authManager.getUserKey());
            profileLoginTime.setProfileKey("last_login_time");
            profileLoginTime.setProfileValue(DateTimeUtils.currentDate("yyyy-MM-dd HH:mm:ss"));
            profiles.add(profileLoginTime);

            AuthManagerProfile profileLoginIp = new AuthManagerProfile();
            profileLoginIp.setUserKey(authManager.getUserKey());
            profileLoginIp.setProfileKey("last_login_ip");
            profileLoginIp.setProfileValue(IpUtils.getIpAddress(request));
            profiles.add(profileLoginIp);
            try {
                authManagerProfileService.saveProfiles(profiles);
            } catch (Exception ex) {
            }
            // ---------------- 记录登录信息(end) ----------------

            return "redirect:/main";
        }
        // 登录失败
        else {
            model.addAttribute("userName", userName);
            model.addAttribute("remember", isRemember);
            model.addAttribute("message", "用户名或者密码错误！");
            return "login";
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
