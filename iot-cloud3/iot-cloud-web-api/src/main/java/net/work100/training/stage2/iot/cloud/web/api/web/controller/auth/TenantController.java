package net.work100.training.stage2.iot.cloud.web.api.web.controller.auth;

import net.work100.training.stage2.iot.cloud.domain.AuthTenant;
import net.work100.training.stage2.iot.cloud.web.api.service.AuthTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RestController
@RequestMapping(value = "auth/tenant")
public class TenantController {

    @Autowired
    private AuthTenantService authTenantService;

    @RequestMapping(value = "{tenantCode}", method = RequestMethod.GET)
    public AuthTenant get(@PathVariable String tenantCode) {
        return authTenantService.getByKey(tenantCode);
    }
}
