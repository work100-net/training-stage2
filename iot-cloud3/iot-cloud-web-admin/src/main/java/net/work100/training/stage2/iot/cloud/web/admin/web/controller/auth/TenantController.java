package net.work100.training.stage2.iot.cloud.web.admin.web.controller.auth;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.validator.BeanValidator;
import net.work100.training.stage2.iot.cloud.domain.AuthTenant;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.TenantSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthTenantService;
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

/**
 * <p>Title: TenantController</p>
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
@RequestMapping(value = "auth/tenant")
public class TenantController {

    @Autowired
    private AuthTenantService authTenantService;

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        TenantSearcher tenantSearcher = new TenantSearcher();
        tenantSearcher.setKeyword("");
        tenantSearcher.setAdvanced(false);
        tenantSearcher.setTenantCode("");
        tenantSearcher.setTenantName("");
        model.addAttribute(tenantSearcher);

        return "auth/tenant_list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        AuthTenant authTenant = new AuthTenant();
        model.addAttribute("authTenant", authTenant);
        return "auth/tenant_add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(AuthTenant authTenant, Model model, RedirectAttributes redirectAttributes) {
        String validator = BeanValidator.validator(authTenant);
        if (validator != null) {
            model.addAttribute("baseResult", BaseResult.fail(validator));
            model.addAttribute("authTenant", authTenant);
            return "auth/tenant_add";
        }

        // 新增处理
        BaseResult baseResult = authTenantService.insert(authTenant);
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/tenant/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/tenant_add";
        }
    }

    @RequestMapping(value = "edit/{tenantCode}", method = RequestMethod.GET)
    public String edit(@PathVariable String tenantCode, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(tenantCode)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/tenant/list";
        }
        AuthTenant authTenant = authTenantService.getByKey(tenantCode);
        if (authTenant == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/tenant/list";
        }
        model.addAttribute("authTenant", authTenant);
        return "auth/tenant_edit";
    }

    @RequestMapping(value = "edit/{tenantCode}", method = RequestMethod.POST)
    public String edit(@PathVariable String tenantCode, AuthTenant authTenant, Model model, RedirectAttributes redirectAttributes) {
        // 数据验证
        if (StringUtils.isBlank(tenantCode)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/tenant/list";
        }
        if (!tenantCode.equals(authTenant.getTenantCode())) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("非法请求"));
            return "redirect:/auth/tenant/list";
        }
        String validator = BeanValidator.validator(authTenant);
        if (validator != null) {
            model.addAttribute("baseResult", BaseResult.fail(validator));
            model.addAttribute("authTenant", authTenant);
            return "auth/tenant_edit";
        }

        // 新增处理
        BaseResult baseResult = authTenantService.update(authTenant);
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/tenant/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/tenant_edit";
        }
    }


    @RequestMapping(value = "delete/{tenantCode}", method = RequestMethod.GET)
    public String delete(@PathVariable String tenantCode, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(tenantCode)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/tenant/list";
        }
        AuthTenant authTenant = authTenantService.getByKey(tenantCode);
        if (authTenant == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/tenant/list";
        }
        authTenantService.delete(tenantCode);
        redirectAttributes.addFlashAttribute("baseResult", BaseResult.success(String.format("租户[%s]已被成功删除", authTenant.getTenantName())));
        return "redirect:/auth/tenant/list";
    }

    @ResponseBody
    @RequestMapping(value = "multi-delete", method = RequestMethod.POST)
    public BaseResult multiDelete(String tenantCodes) {
        try {
            String[] arrUserKeys = tenantCodes.split(",");
            if (arrUserKeys == null || arrUserKeys.length <= 0) {
                return BaseResult.fail("请至少选择一条记录");
            }
            authTenantService.multiDelete(arrUserKeys);
            return BaseResult.success("操作成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "page-search", method = RequestMethod.POST)
    public PageInfo<AuthTenant> pageSearch(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        String strAdvanced = request.getParameter("advanced");
        String keyword = request.getParameter("keyword");
        String tenantCode = request.getParameter("tenantCode");
        String tenantName = request.getParameter("tenantName");

        int draw = StringUtils.isBlank(strDraw) ? 0 : Integer.parseInt(strDraw);
        int start = StringUtils.isBlank(strStart) ? 0 : Integer.parseInt(strStart);
        int length = StringUtils.isBlank(strLength) ? 0 : Integer.parseInt(strLength);
        boolean advanced = StringUtils.isBlank(strAdvanced) ? false : Boolean.parseBoolean(strAdvanced);

        TenantSearcher tenantSearcher = new TenantSearcher();
        if (!advanced) {
            tenantSearcher.setAdvanced(advanced);
            tenantSearcher.setKeyword(keyword);

        } else {
            tenantSearcher.setAdvanced(advanced);
            tenantSearcher.setTenantCode(tenantCode);
            tenantSearcher.setTenantName(tenantName);
        }

        tenantSearcher.setStart(start);
        tenantSearcher.setLength(length);

        return authTenantService.pageSearch(draw, tenantSearcher);
    }
}
