package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.utils.EncryptionUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerDao;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void insert(AuthManager authManager) {
        authManagerDao.insert(authManager);
    }

    @Override
    public void delete(Long id) {
        authManagerDao.delete(id);
    }

    @Override
    public AuthManager getById(Long id) {
        return authManagerDao.getById(id);
    }

    @Override
    public void update(AuthManager authManager) {
        authManagerDao.update(authManager);
    }

    @Override
    public List<AuthManager> selectByName(String userName) {
        return authManagerDao.selectByName(userName);
    }

    @Override
    public AuthManager login(String userName, String password) {
        AuthManager authManager = authManagerDao.getByUserName(userName);
        if (authManager != null) {
            // 验证密码，如果验证通过，则返回用户信息
            if (EncryptionUtils.validateEncryptPassword(password, authManager.getPassword())) {
                return authManager;
            }
        }
        return null;
    }
}
