package net.work100.training.stage2.iot.cloud.web.api.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.service.impl.ApiAbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.commons.utils.EncryptionUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.api.dao.AuthTenantUserDao;
import net.work100.training.stage2.iot.cloud.web.api.dto.auth.TenantUserSearcher;
import net.work100.training.stage2.iot.cloud.web.api.service.AuthTenantUserService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * <p>Title: AuthTenantUserServiceImpl</p>
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
public class AuthTenantUserServiceImpl extends ApiAbstractBaseServiceImpl<AuthTenantUser, TenantUserSearcher, AuthTenantUserDao> implements AuthTenantUserService {

    @Autowired
    private AuthTenantUserDao authTenantUserDao;

    @Override
    @Transactional(readOnly = false)
    public BaseResult insert(String apiTenantCode, AuthTenantUser authTenantUser) {
        if (authTenantUserDao.getByUserName(apiTenantCode, authTenantUser.getUserName()) != null) {
            return BaseResult.fail("用户名已经存在");
        }
        try {
            // 生成 userKey
            authTenantUser.setUserKey(generateUserKey(apiTenantCode, authTenantUser.getUserName()));

            // 密码加密
            authTenantUser.setPassword(EncryptionUtils.encryptPassword(EncryptionUtils.EncryptionType.MD5, authTenantUser.getPassword()));
            authTenantUser.setCreated(new Date());
            authTenantUser.setUpdated(new Date());

            authTenantUserDao.insert(apiTenantCode, authTenantUser);
            return BaseResult.success("新增账户成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult update(String apiTenantCode, AuthTenantUser authTenantUser) {
        if (authTenantUserDao.getByKey(apiTenantCode, authTenantUser.getUserKey()) == null) {
            return BaseResult.fail("用户不存在");
        }
        try {
            authTenantUser.setUpdated(new Date());

            authTenantUserDao.update(apiTenantCode, authTenantUser);
            return BaseResult.success("账户更新成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    public AuthTenantUser login(String apiTenantCode, String userName, String password) {
        AuthTenantUser authTenantUser = authTenantUserDao.getByUserName(apiTenantCode, userName);
        if (authTenantUser != null && authTenantUser.getStatus() == 1) {
            // 验证密码，如果验证通过，则返回用户信息
            if (EncryptionUtils.validateEncryptPassword(password, authTenantUser.getPassword())) {
                return authTenantUser;
            }
        }
        return null;
    }

    /**
     * 生成 userKey
     *
     * @param apiTenantCode 租户编码
     * @param userName      用户名
     * @return
     */
    private String generateUserKey(String apiTenantCode, String userName) {
        String strDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String sourceUserKey = String.format("%s%s%s", apiTenantCode.toLowerCase(), userName.toLowerCase(), strDate);
        return EncryptionUtils.encryptText(EncryptionUtils.EncryptionType.MD5, sourceUserKey);
    }

}
