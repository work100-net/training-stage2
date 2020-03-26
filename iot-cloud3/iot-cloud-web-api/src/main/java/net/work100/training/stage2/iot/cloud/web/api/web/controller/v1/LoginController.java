package net.work100.training.stage2.iot.cloud.web.api.web.controller.v1;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.api.service.AuthTenantUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Title: LoginController</p>
 * <p>Description: 用户登录API</p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-api.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-26 12:45
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-26   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@RestController
@RequestMapping(value = "${api.path.v1}/auth/tenant/{apiTenantCode}")
public class LoginController {

    @Autowired
    private AuthTenantUserService authTenantUserService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResult login(@PathVariable(required = true) String apiTenantCode,
                            @RequestParam(required = true) String userName,
                            @RequestParam(required = true) String password) {
        try {
            TenantUserDTO tenantUserDTO = new TenantUserDTO();
            AuthTenantUser authTenantUser = authTenantUserService.login(apiTenantCode, userName, password);
            if (authTenantUser != null) {
                BeanUtils.copyProperties(authTenantUser, tenantUserDTO);
            }
            return BaseResult.success("操作成功", tenantUserDTO);
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }
}
