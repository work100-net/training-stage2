package net.work100.training.stage2.iot.cloud.web.admin.web.controller.auth;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.validator.BeanValidator;
import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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
        ManagerSearcher managerSearcher = new ManagerSearcher();
        managerSearcher.setKeyword("");
        managerSearcher.setAdvanced(false);
        managerSearcher.setRoles("");
        managerSearcher.setStatus(-1);
        model.addAttribute(managerSearcher);

        List<AuthManager> authManagers = authManagerService.selectAll();
        model.addAttribute("authManagers", authManagers);
        return "auth/manager_list";
    }

    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(ManagerSearcher managerSearcher, Model model) {

        List<AuthManager> authManagers = authManagerService.search(managerSearcher);
        model.addAttribute("managerSearcher", managerSearcher);
        model.addAttribute("authManagers", authManagers);
        return "auth/manager_list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        AuthManager authManager = new AuthManager();
        model.addAttribute("authManager", authManager);
        return "auth/manager_add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(AuthManager authManager, Model model, RedirectAttributes redirectAttributes) {
        String validator = BeanValidator.validator(authManager);
        if(validator!=null){
            model.addAttribute("baseResult", BaseResult.fail(validator));
            model.addAttribute("authManager", authManager);
            return "auth/manager_add";
        }

        // 新增处理
        BaseResult baseResult = authManagerService.insert(authManager);
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/manager/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/manager_add";
        }
    }

    @RequestMapping(value = "edit/{userKey}", method = RequestMethod.GET)
    public String edit(@PathVariable String userKey, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/manager/list";
        }
        AuthManager authManager = authManagerService.getByKey(userKey);
        if (authManager == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/manager/list";
        }
        model.addAttribute("authManager", authManager);
        return "auth/manager_edit";
    }

    @RequestMapping(value = "edit/{userKey}", method = RequestMethod.POST)
    public String edit(@PathVariable String userKey, AuthManager authManager, Model model, RedirectAttributes redirectAttributes) {
        // 数据验证
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/manager/list";
        }
        if (!userKey.equals(authManager.getUserKey())) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("非法请求"));
            return "redirect:/auth/manager/list";
        }
        String validator = BeanValidator.validator(authManager);
        if(validator!=null){
            model.addAttribute("baseResult", BaseResult.fail(validator));
            model.addAttribute("authManager", authManager);
            return "auth/manager_edit";
        }

        // 新增处理
        BaseResult baseResult = authManagerService.update(authManager);
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/manager/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/manager_edit";
        }
    }


    @RequestMapping(value = "delete/{userKey}", method = RequestMethod.GET)
    public String delete(@PathVariable String userKey, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/manager/list";
        }
        AuthManager authManager = authManagerService.getByKey(userKey);
        if (authManager == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/manager/list";
        }
        authManagerService.delete(userKey);
        redirectAttributes.addFlashAttribute("baseResult", BaseResult.success(String.format("账户[%s]已被成功删除", authManager.getUserName())));
        return "redirect:/auth/manager/list";
    }

    @ResponseBody
    @RequestMapping(value = "multi-delete", method = RequestMethod.POST)
    public BaseResult multiDelete(String userKeys) {
        try {
            String[] arrUserKeys = userKeys.split(",");
            if (arrUserKeys == null || arrUserKeys.length <= 0) {
                return BaseResult.fail("请至少选择一条记录");
            }
            for (String userKey : arrUserKeys) {
                if ("8c41b9a54b2e2a4180cc1271b4672779".equals(userKey)) {
                    return BaseResult.fail("不能删除默认管理员账号:xiaojun.liu");
                }
            }
            authManagerService.multiDelete(arrUserKeys);
            return BaseResult.success("操作成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "page-search", method = RequestMethod.POST)
    public PageInfo<AuthManager> page(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        String strAdvanced = request.getParameter("advanced");
        String keyword = request.getParameter("keyword");
        String userName = request.getParameter("userName");
        String roles = request.getParameter("roles");
        String strStatus = request.getParameter("status");

        int draw = strDraw == null ? 0 : Integer.parseInt(strDraw);
        int start = strStart == null ? 0 : Integer.parseInt(strStart);
        int length = strLength == null ? 0 : Integer.parseInt(strLength);
        boolean advanced = strAdvanced == null ? false : Boolean.parseBoolean(strAdvanced);
        int status = strStatus == null ? 0 : Integer.parseInt(strStatus);

        ManagerSearcher managerSearcher = new ManagerSearcher();
        managerSearcher.setAdvanced(advanced);
        managerSearcher.setKeyword(keyword);
        managerSearcher.setUserName(userName);
        managerSearcher.setRoles(roles);
        managerSearcher.setStatus(status);

        return authManagerService.pageSearch(draw, start, length, managerSearcher);
    }
}
