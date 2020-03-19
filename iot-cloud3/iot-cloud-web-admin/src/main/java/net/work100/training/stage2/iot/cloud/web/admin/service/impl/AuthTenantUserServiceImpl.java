package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.service.impl.AbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.commons.utils.EncryptionUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthTenantUserDao;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.TenantUserSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthTenantUserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * <p>Title: AuthTenantUserServiceImpl</p>
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
public class AuthTenantUserServiceImpl extends AbstractBaseServiceImpl<AuthTenantUser, TenantUserSearcher, AuthTenantUserDao> implements AuthTenantUserService {

    @Autowired
    private AuthTenantUserDao authTenantUserDao;

    @Override
    public BaseResult insert(AuthTenantUser authTenantUser) {
        if (authTenantUserDao.getByUserName(authTenantUser.getTenantCode(), authTenantUser.getUserName()) != null) {
            return BaseResult.fail("用户名已经存在");
        }
        try {
            // 生成 userKey
            authTenantUser.setUserKey(generateUserKey(authTenantUser.getTenantCode(), authTenantUser.getUserName()));

            // 密码加密
            authTenantUser.setPassword(EncryptionUtils.encryptPassword(EncryptionUtils.EncryptionType.MD5, authTenantUser.getPassword()));
            authTenantUser.setCreated(new Date());
            authTenantUser.setUpdated(new Date());

            authTenantUserDao.insert(authTenantUser);
            return BaseResult.success("新增账户成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    public BaseResult update(AuthTenantUser authTenantUser) {
        if (authTenantUserDao.getByKey(authTenantUser.getUserKey()) == null) {
            return BaseResult.fail("用户不存在");
        }
        try {
            authTenantUser.setUpdated(new Date());

            authTenantUserDao.update(authTenantUser);
            return BaseResult.success("账户更新成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 生成 userKey
     *
     * @param tenantCode 租户编码
     * @param userName   用户名
     * @return
     */
    private String generateUserKey(String tenantCode, String userName) {
        String strDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String sourceUserKey = String.format("%s%s%s", tenantCode.toLowerCase(), userName.toLowerCase(), strDate);
        return EncryptionUtils.encryptText(EncryptionUtils.EncryptionType.MD5, sourceUserKey);
    }

}
