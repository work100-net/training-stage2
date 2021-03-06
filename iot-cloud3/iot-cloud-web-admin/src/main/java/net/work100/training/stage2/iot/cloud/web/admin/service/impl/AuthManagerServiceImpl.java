package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.service.impl.AbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.commons.utils.EncryptionUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerDao;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * <p>Title: AuthManagerServiceImpl</p>
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
@Transactional(readOnly = true)
public class AuthManagerServiceImpl extends AbstractBaseServiceImpl<AuthManager, ManagerSearcher, AuthManagerDao> implements AuthManagerService {

    @Autowired
    private AuthManagerDao authManagerDao;

    @Override
    @Transactional(readOnly = false)
    public BaseResult insert(AuthManager authManager) {
        if (authManagerDao.getByUserName(authManager.getUserName()) != null) {
            return BaseResult.fail("用户名已经存在");
        }
        try {
            // 生成 userKey
            authManager.setUserKey(generateUserKey(authManager.getUserName()));

            // 密码加密
            authManager.setPassword(EncryptionUtils.encryptPassword(EncryptionUtils.EncryptionType.MD5, authManager.getPassword()));
            authManager.setCreated(new Date());
            authManager.setUpdated(new Date());

            authManagerDao.insert(authManager);
            return BaseResult.success("新增账户成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult update(AuthManager authManager) {
        if (authManagerDao.getByKey(authManager.getUserKey()) == null) {
            return BaseResult.fail("用户不存在");
        }
        try {
            authManager.setUpdated(new Date());

            authManagerDao.update(authManager);
            return BaseResult.success("账户更新成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    public AuthManager login(String userName, String password) {
        AuthManager authManager = authManagerDao.getByUserName(userName);
        if (authManager != null && authManager.getStatus() == 1) {
            // 验证密码，如果验证通过，则返回用户信息
            if (EncryptionUtils.validateEncryptPassword(password, authManager.getPassword())) {
                return authManager;
            }
        }
        return null;
    }

    /**
     * 生成 userKey
     *
     * @param userName 用户名
     * @return
     */
    private String generateUserKey(String userName) {
        String strDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss");
        String sourceUserKey = String.format("%s%s", userName.toLowerCase(), strDate);
        return EncryptionUtils.encryptText(EncryptionUtils.EncryptionType.MD5, sourceUserKey);
    }

}
