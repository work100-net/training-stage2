package net.work100.training.stage2.iot.cloud.web.admin.web.controller.auth;

import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>Title: ManagerController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-01 14:01
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-01   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Controller
@RequestMapping(value = "auth/manager")
public class ManagerController {

    @Autowired
    private AuthManagerService authManagerService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        List<AuthManager> authManagers = authManagerService.selectAll();
        model.addAttribute("authManagers", authManagers);
        System.out.println("--------------------------------------------------");
        System.out.println(authManagers);
        System.out.println("--------------------------------------------------");
        return "auth/manager_list";
    }
}
