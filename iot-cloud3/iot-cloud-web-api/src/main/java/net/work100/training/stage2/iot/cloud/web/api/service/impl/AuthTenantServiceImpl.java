package net.work100.training.stage2.iot.cloud.web.api.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.service.impl.AbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.domain.AuthTenant;
import net.work100.training.stage2.iot.cloud.web.api.dao.AuthTenantDao;
import net.work100.training.stage2.iot.cloud.web.api.dto.auth.TenantSearcher;
import net.work100.training.stage2.iot.cloud.web.api.service.AuthTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>Title: AuthTenantServiceImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-api.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 23:06
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Service
@Transactional(readOnly = true)
public class AuthTenantServiceImpl extends AbstractBaseServiceImpl<AuthTenant, TenantSearcher, AuthTenantDao> implements AuthTenantService {

    @Autowired
    private AuthTenantDao authTenantDao;

    @Override
    @Transactional(readOnly = false)
    public BaseResult insert(AuthTenant authTenant) {
        return null;
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult update(AuthTenant authTenant) {
        return null;
    }

}
