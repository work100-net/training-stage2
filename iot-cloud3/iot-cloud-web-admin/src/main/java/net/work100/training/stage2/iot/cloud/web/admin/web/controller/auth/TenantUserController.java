package net.work100.training.stage2.iot.cloud.web.admin.web.controller.auth;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.validator.BeanValidator;
import net.work100.training.stage2.iot.cloud.domain.AuthTenant;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.TenantUserSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthTenantService;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthTenantUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>Title: TenantUserController</p>
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
@RequestMapping(value = "auth/tenant-user")
public class TenantUserController {

    @Autowired
    private AuthTenantService authTenantService;

    @Autowired
    private AuthTenantUserService authTenantUserService;


    @ModelAttribute
    public void init(Model model, @RequestParam(required = false) String tenantCode) {
        List<AuthTenant> authTenants = authTenantService.selectAll();
        model.addAttribute("authTenants", authTenants);
        if (tenantCode == null) {
            if (authTenants.size() > 0) {
                tenantCode = authTenants.get(0).getTenantCode();
            }
        }
        model.addAttribute("tenantCode", tenantCode);
    }


    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model, @RequestParam(required = false) String tenantCode) {
        TenantUserSearcher tenantUserSearcher = new TenantUserSearcher();
        tenantUserSearcher.setTenantCode(tenantCode);
        tenantUserSearcher.setKeyword("");
        tenantUserSearcher.setAdvanced(false);
        tenantUserSearcher.setRoles("");
        tenantUserSearcher.setStatus(-1);
        model.addAttribute(tenantUserSearcher);

        return "auth/tenant_user_list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model, @RequestParam(required = false) String tenantCode) {
        AuthTenantUser authTenantUser = new AuthTenantUser();
        authTenantUser.setTenantCode(tenantCode);
        model.addAttribute("authTenantUser", authTenantUser);
        return "auth/tenant_user_add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(AuthTenantUser authTenantUser, Model model, RedirectAttributes redirectAttributes) {
        String validator = BeanValidator.validator(authTenantUser);
        if (validator != null) {
            model.addAttribute("baseResult", BaseResult.fail(validator));
            model.addAttribute("authTenantUser", authTenantUser);
            return "auth/tenant_user_add";
        }

        // 新增处理
        BaseResult baseResult = authTenantUserService.insert(authTenantUser);
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/tenant-user/list?tenantCode=" + authTenantUser.getTenantCode();
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/tenant_user_add";
        }
    }

    @RequestMapping(value = "edit/{userKey}", method = RequestMethod.GET)
    public String edit(@PathVariable String userKey, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/tenant-user/list";
        }
        AuthTenantUser authTenantUser = authTenantUserService.getByKey(userKey);
        if (authTenantUser == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/tenant-user/list";
        }
        model.addAttribute("authTenantUser", authTenantUser);
        return "auth/tenant_user_edit";
    }

    @RequestMapping(value = "edit/{userKey}", method = RequestMethod.POST)
    public String edit(@PathVariable String userKey, AuthTenantUser authTenantUser, Model model, RedirectAttributes redirectAttributes, @RequestParam(required = false) String tenantCode) {
        // 数据验证
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/tenant-user/list";
        }
        if (!userKey.equals(authTenantUser.getUserKey())) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("非法请求"));
            return "redirect:/auth/tenant-user/list?tenantCode=" + tenantCode;
        }
        String validator = BeanValidator.validator(authTenantUser);
        if (validator != null) {
            model.addAttribute("baseResult", BaseResult.fail(validator));
            model.addAttribute("authTenantUser", authTenantUser);
            return "auth/tenant_user_edit";
        }

        // 新增处理
        BaseResult baseResult = authTenantUserService.update(authTenantUser);
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/tenant-user/list?tenantCode=" + tenantCode;
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/tenant_user_edit";
        }
    }


    @RequestMapping(value = "delete/{userKey}", method = RequestMethod.GET)
    public String delete(@PathVariable String userKey, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/tenant-user/list";
        }
        AuthTenantUser authTenantUser = authTenantUserService.getByKey(userKey);
        if (authTenantUser == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/tenant-user/list";
        }
        authTenantUserService.delete(userKey);
        redirectAttributes.addFlashAttribute("baseResult", BaseResult.success(String.format("账户[%s]已被成功删除", authTenantUser.getUserName())));
        return "redirect:/auth/tenant-user/list?tenantCode=" + authTenantUser.getTenantCode();
    }

    @ResponseBody
    @RequestMapping(value = "multi-delete", method = RequestMethod.POST)
    public BaseResult multiDelete(String userKeys) {
        try {
            String[] arrUserKeys = userKeys.split(",");
            if (arrUserKeys == null || arrUserKeys.length <= 0) {
                return BaseResult.fail("请至少选择一条记录");
            }
            authTenantUserService.multiDelete(arrUserKeys);
            return BaseResult.success("操作成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "page-search", method = RequestMethod.POST)
    public PageInfo<AuthTenantUser> pageSearch(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        String strAdvanced = request.getParameter("advanced");
        String tenantCode = request.getParameter("tenantCode");
        String keyword = request.getParameter("keyword");
        String userName = request.getParameter("userName");
        String roles = request.getParameter("roles");
        String strStatus = request.getParameter("status");

        int draw = StringUtils.isBlank(strDraw) ? 0 : Integer.parseInt(strDraw);
        int start = StringUtils.isBlank(strStart) ? 0 : Integer.parseInt(strStart);
        int length = StringUtils.isBlank(strLength) ? 0 : Integer.parseInt(strLength);
        boolean advanced = StringUtils.isBlank(strAdvanced) ? false : Boolean.parseBoolean(strAdvanced);
        int status = StringUtils.isBlank(strStatus) ? -1 : Integer.parseInt(strStatus);

        TenantUserSearcher tenantUserSearcher = new TenantUserSearcher();
        tenantUserSearcher.setAdvanced(advanced);
        tenantUserSearcher.setTenantCode(tenantCode);
        if (!advanced) {
            tenantUserSearcher.setKeyword(keyword);

        } else {
            tenantUserSearcher.setUserName(userName);
            tenantUserSearcher.setRoles(roles);
            tenantUserSearcher.setStatus(status);
        }

        tenantUserSearcher.setStart(start);
        tenantUserSearcher.setLength(length);

        return authTenantUserService.pageSearch(draw, tenantUserSearcher);
    }
}
