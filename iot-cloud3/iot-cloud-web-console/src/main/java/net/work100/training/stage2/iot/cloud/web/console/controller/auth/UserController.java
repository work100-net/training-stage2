package net.work100.training.stage2.iot.cloud.web.console.controller.auth;

import net.work100.training.stage2.iot.cloud.commons.constant.ConstantUtils;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.SessionUtils;
import net.work100.training.stage2.iot.cloud.web.console.api.TenantUserApi;
import net.work100.training.stage2.iot.cloud.web.console.dto.auth.TenantUserSearcher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: UserController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-console.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-01 14:01
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-01   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Controller
@RequestMapping(value = "auth/user")
public class UserController {

    private TenantUserDTO me = null;

    @ModelAttribute
    private void init(HttpServletRequest request) {
        me = SessionUtils.get(request, ConstantUtils.SESSION_TENANT_USER);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        TenantUserSearcher tenantUserSearcher = new TenantUserSearcher();
        tenantUserSearcher.setKeyword("");
        tenantUserSearcher.setAdvanced(false);
        tenantUserSearcher.setRoles("");
        tenantUserSearcher.setStatus(-1);
        model.addAttribute(tenantUserSearcher);

        return "auth/user_list";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        TenantUserDTO tenantUserDTO = new TenantUserDTO();
        model.addAttribute("tenantUserDTO", tenantUserDTO);
        return "auth/user_add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(TenantUserDTO tenantUserDTO, Model model, RedirectAttributes redirectAttributes) {
        // 新增处理
        BaseResult baseResult = TenantUserApi.add(
                me.getTenantCode(),
                tenantUserDTO.getUserName(),
                tenantUserDTO.getPassword(),
                tenantUserDTO.getStatus(),
                tenantUserDTO.isSuperuser(),
                tenantUserDTO.getRoles());
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/user/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/user_add";
        }
    }

    @RequestMapping(value = "edit/{userKey}", method = RequestMethod.GET)
    public String edit(@PathVariable String userKey, Model model, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/user/list";
        }
        TenantUserDTO tenantUserDTO = TenantUserApi.get(me.getTenantCode(), userKey);
        if (tenantUserDTO == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/user/list";
        }
        model.addAttribute("tenantUserDTO", tenantUserDTO);
        return "auth/user_edit";
    }

    @RequestMapping(value = "edit/{userKey}", method = RequestMethod.POST)
    public String edit(@PathVariable String userKey, TenantUserDTO tenantUserDTO, Model model, RedirectAttributes redirectAttributes) {
        // 数据验证
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/user/list";
        }
        if (!userKey.equals(tenantUserDTO.getUserKey())) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("非法请求"));
            return "redirect:/auth/user/list";
        }

        // 新增处理
        BaseResult baseResult = TenantUserApi.edit(
                me.getTenantCode(),
                tenantUserDTO.getUserKey(),
                tenantUserDTO.getStatus(),
                tenantUserDTO.isSuperuser(),
                tenantUserDTO.getRoles());
        if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/user/list";
        } else {
            model.addAttribute("baseResult", baseResult);
            return "auth/user_edit";
        }
    }


    @RequestMapping(value = "delete/{userKey}", method = RequestMethod.GET)
    public String delete(@PathVariable String userKey, RedirectAttributes redirectAttributes) {
        if (StringUtils.isBlank(userKey)) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("参数为空"));
            return "redirect:/auth/user/list";
        }
        TenantUserDTO tenantUserDTO = TenantUserApi.get(me.getTenantCode(), userKey);
        if (tenantUserDTO == null) {
            redirectAttributes.addFlashAttribute("baseResult", BaseResult.fail("数据不存在"));
            return "redirect:/auth/user/list";
        }
        BaseResult baseResult = TenantUserApi.delete(me.getTenantCode(), userKey);
        if (baseResult.getStatus() != HttpUtils.HTTP_STATUS_CODE_OK) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/auth/user/list";
        }
        redirectAttributes.addFlashAttribute("baseResult", BaseResult.success(String.format("账户[%s]已被成功删除", tenantUserDTO.getUserName())));
        return "redirect:/auth/user/list";
    }

    @ResponseBody
    @RequestMapping(value = "multi-delete", method = RequestMethod.POST)
    public BaseResult multiDelete(String userKeys) {
        try {
            String[] arrUserKeys = userKeys.split(",");
            if (arrUserKeys == null || arrUserKeys.length <= 0) {
                return BaseResult.fail("请至少选择一条记录");
            }
            return TenantUserApi.multiDelete(me.getTenantCode(), arrUserKeys);
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @ResponseBody
    @RequestMapping(value = "page-search", method = RequestMethod.POST)
    public PageInfo<TenantUserDTO> pageSearch(HttpServletRequest request) {
        String strDraw = request.getParameter("draw");
        String strStart = request.getParameter("start");
        String strLength = request.getParameter("length");
        String strAdvanced = request.getParameter("advanced");
        String keyword = request.getParameter("keyword");
        String userName = request.getParameter("userName");
        String roles = request.getParameter("roles");
        String strStatus = request.getParameter("status");

        int draw = StringUtils.isBlank(strDraw) ? 0 : Integer.parseInt(strDraw);
        int start = StringUtils.isBlank(strStart) ? 0 : Integer.parseInt(strStart);
        int length = StringUtils.isBlank(strLength) ? 0 : Integer.parseInt(strLength);
        boolean advanced = StringUtils.isBlank(strAdvanced) ? false : Boolean.parseBoolean(strAdvanced);
        int status = StringUtils.isBlank(strStatus) ? -1 : Integer.parseInt(strStatus);

        return TenantUserApi.pageSearch(me.getTenantCode(), draw, start, length, advanced, keyword, userName, roles, status);
    }
}
