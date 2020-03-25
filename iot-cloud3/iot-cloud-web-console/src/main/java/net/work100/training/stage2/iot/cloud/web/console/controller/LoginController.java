package net.work100.training.stage2.iot.cloud.web.console.controller;

import net.work100.training.stage2.iot.cloud.commons.constant.ConstantUtils;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantDTO;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.commons.utils.CookieUtils;
import net.work100.training.stage2.iot.cloud.web.console.api.TenantApi;
import net.work100.training.stage2.iot.cloud.web.console.dto.LoginDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Title: LoginController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-console.html</p>
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

    @ModelAttribute
    private void init(HttpServletRequest request, Model model) {
        LoginDTO loginDTO = new LoginDTO();
        boolean isRemember = "on".equals(CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_TENANT_USER_REMEMBER));
        loginDTO.setRemember(isRemember);
        if (isRemember) {
            String tenantCode = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_TENANT_CODE);
            String userName = CookieUtils.getCookieValue(request, ConstantUtils.COOKIE_TENANT_USER_USERNAME);
            loginDTO.setTenantCode(tenantCode);
            loginDTO.setUserName(userName);
        }
        model.addAttribute("loginDTO", loginDTO);
    }

    /**
     * 登录页面
     *
     * @return
     */
    @RequestMapping(value = {"", "login"}, method = RequestMethod.GET)
    public String login() {
        TenantDTO tenantDTO = TenantApi.get("100001");
        System.out.println(tenantDTO);
        return "login";
    }

    /**
     * 登录验证
     *
     * @param loginDTO
     * @param model
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(LoginDTO loginDTO,
                        Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        TenantUserDTO tenantUserDTO = null;

        // 登录成功
        if (tenantUserDTO != null) {
            // 处理记住我功能(记住用户名)
            if (loginDTO.isRemember()) {
                // Cookie 存储一周
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_TENANT_USER_REMEMBER, "on", 7 * 24 * 60 * 60);
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_TENANT_CODE, loginDTO.getTenantCode(), 7 * 24 * 60 * 60);
                CookieUtils.setCookie(request, response, ConstantUtils.COOKIE_TENANT_USER_USERNAME, loginDTO.getUserName(), 7 * 24 * 60 * 60);
            } else {
                // 删除 Cookie
                CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_TENANT_USER_REMEMBER);
                CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_TENANT_CODE);
                CookieUtils.deleteCookie(request, response, ConstantUtils.COOKIE_TENANT_USER_USERNAME);
            }
            // 将登录信息记入Session
            request.getSession().setAttribute(ConstantUtils.SESSION_TENANT_USER, tenantUserDTO);

            return "redirect:/main";
        }
        // 登录失败
        else {
            loginDTO.setPassword("");
            model.addAttribute("loginDTO", loginDTO);
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
