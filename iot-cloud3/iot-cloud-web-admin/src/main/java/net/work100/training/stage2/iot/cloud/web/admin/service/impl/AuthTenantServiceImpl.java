package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.service.impl.AbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.domain.AuthTenant;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthTenantDao;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.TenantSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>Title: AuthTenantServiceImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 23:06
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Service
public class AuthTenantServiceImpl extends AbstractBaseServiceImpl<AuthTenant, TenantSearcher, AuthTenantDao> implements AuthTenantService {

    @Autowired
    private AuthTenantDao authTenantDao;

    @Override
    public BaseResult insert(AuthTenant authTenant) {
        if (authTenantDao.getByKey(authTenant.getTenantCode()) != null) {
            return BaseResult.fail("租户编码已经存在");
        }
        try {
            authTenant.setCreated(new Date());
            authTenant.setUpdated(new Date());

            authTenantDao.insert(authTenant);
            return BaseResult.success("新增租户成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    public BaseResult update(AuthTenant authTenant) {
        if (authTenantDao.getByKey(authTenant.getTenantCode()) == null) {
            return BaseResult.fail("租户不存在");
        }
        try {
            authTenant.setUpdated(new Date());

            authTenantDao.update(authTenant);
            return BaseResult.success("租户更新成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

}
