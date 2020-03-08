package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.utils.EncryptionUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerDao;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

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
public class AuthManagerServiceImpl implements AuthManagerService {

    @Autowired
    private AuthManagerDao authManagerDao;

    @Override
    public List<AuthManager> selectAll() {
        return authManagerDao.selectAll();
    }

    @Override
    public BaseResult insert(AuthManager authManager) {
        if (authManagerDao.getByUserName(authManager.getUserName()) != null) {
            return BaseResult.fail("用户名已经存在");
        }
        try {
            // 生成 userKey
            authManager.setUserKey(EncryptionUtils.encryptText(EncryptionUtils.EncryptionType.MD5, authManager.getUserName().toLowerCase()));

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
    public void delete(String userKey) {
        authManagerDao.delete(userKey);
    }

    @Override
    public AuthManager getById(Long id) {
        return authManagerDao.getById(id);
    }

    @Override
    public BaseResult update(AuthManager authManager) {
        if (authManagerDao.getByUserKey(authManager.getUserKey()) == null) {
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
    public List<AuthManager> selectByName(String userName) {
        return authManagerDao.selectByName(userName);
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

    @Override
    public AuthManager getByUserKey(String userKey) {
        return authManagerDao.getByUserKey(userKey);
    }

    @Override
    public List<AuthManager> search(ManagerSearcher managerSearcher) {
        AuthManager authManager = new AuthManager();
        if (!managerSearcher.isAdvanced()) {
            authManager.setUserName(managerSearcher.getKeyword());
            authManager.setRoles("");
            authManager.setStatus(-1);
        } else {
            authManager.setUserName(managerSearcher.getUserName());
            authManager.setRoles(managerSearcher.getRoles());
            authManager.setStatus(managerSearcher.getStatus());
        }
        return authManagerDao.search(authManager);
    }

    /**
     * 生成 userKey
     *
     * @param userName 用户名
     * @return
     */
    private String generateUserKey(String userName) {
        String strDate = DateFormatUtils.format(new Date(), "yyyy-MM-ddTHH:mm");
        String sourceUserKey = String.format("%s%s", userName.toLowerCase(), strDate);
        return EncryptionUtils.encryptText(EncryptionUtils.EncryptionType.MD5, sourceUserKey);
    }

}
