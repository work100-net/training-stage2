package net.work100.training.stage2.iot.cloud.web.admin.service;

import net.work100.training.stage2.iot.cloud.commons.service.BaseService;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.TenantUserSearcher;

/**
 * <p>Title: AuthTenantUserService</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 23:01
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface AuthTenantUserService extends BaseService<AuthTenantUser, TenantUserSearcher> {

    /**
     * 登录验证
     *
     * @param tenantCode 租户编码
     * @param userName   用户名
     * @param password   密码
     * @return
     */
    AuthTenantUser login(String tenantCode, String userName, String password);

}
