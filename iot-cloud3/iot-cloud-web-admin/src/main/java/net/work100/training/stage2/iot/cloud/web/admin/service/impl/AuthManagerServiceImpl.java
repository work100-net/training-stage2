package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.service.impl.AbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.commons.utils.EncryptionUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerDao;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class AuthManagerServiceImpl extends AbstractBaseServiceImpl<AuthManager, ManagerSearcher, AuthManagerDao> implements AuthManagerService {

    @Autowired
    private AuthManagerDao authManagerDao;

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

    @Override
    public PageInfo<AuthManager> pageSearch(int draw, int start, int length, ManagerSearcher managerSearcher) {
        Map<String, Object> params = new HashMap<>();
        params.put("start", start);
        params.put("length", length);
        AuthManager authManager = new AuthManager();

        if (!managerSearcher.isAdvanced()) {
            authManager.setUserName(managerSearcher.getKeyword());
            authManager.setRoles("");
            authManager.setStatus(-1);

            params.put("userName", managerSearcher.getKeyword());
            params.put("roles", "");
            params.put("status", -1);
        } else {
            authManager.setUserName(managerSearcher.getUserName());
            authManager.setRoles(managerSearcher.getRoles());
            authManager.setStatus(managerSearcher.getStatus());

            params.put("userName", managerSearcher.getUserName());
            params.put("roles", managerSearcher.getRoles());
            params.put("status", managerSearcher.getStatus());
        }

        // 处理分页结果
        PageInfo<AuthManager> authManagerPageInfo = new PageInfo<>();
        authManagerPageInfo.setDraw(draw);

        // 获取记录数
        int recordsTotal = authManagerDao.count(authManager);
        authManagerPageInfo.setRecordsTotal(recordsTotal);
        authManagerPageInfo.setRecordsFiltered(recordsTotal);

        // 获取分页数据
        List<AuthManager> data = authManagerDao.pageSearch(params);
        authManagerPageInfo.setData(data);

        return authManagerPageInfo;
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
