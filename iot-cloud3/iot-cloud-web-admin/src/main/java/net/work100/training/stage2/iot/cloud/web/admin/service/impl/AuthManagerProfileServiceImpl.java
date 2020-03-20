package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.service.impl.AbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.domain.AuthManagerProfile;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerProfileDao;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerProfileSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: AuthManagerProfileServiceImpl</p>
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
public class AuthManagerProfileServiceImpl extends AbstractBaseServiceImpl<AuthManagerProfile, ManagerProfileSearcher, AuthManagerProfileDao> implements AuthManagerProfileService {

    @Autowired
    private AuthManagerProfileDao authManagerProfileDao;

    @Override
    public void deleteProfile(String userKey, String profileKey) {
        this.delete(generateEntityKey(userKey, profileKey));
    }

    @Override
    public void deleteProfiles(String userKey) {
        this.multiDelete(new String[]{userKey});
    }

    @Override
    public BaseResult insert(AuthManagerProfile authManagerProfile) {
        String entityKey = String.format("%s,%s", authManagerProfile.getUserKey(), authManagerProfile.getProfileKey());
        if (authManagerProfileDao.getByKey(entityKey) != null) {
            return BaseResult.fail("属性已经存在");
        }
        try {
            authManagerProfile.setCreated(new Date());
            authManagerProfile.setUpdated(new Date());

            authManagerProfileDao.insert(authManagerProfile);
            return BaseResult.success("新增属性成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    public BaseResult update(AuthManagerProfile authManagerProfile) {
        String entityKey = String.format("%s,%s", authManagerProfile.getUserKey(), authManagerProfile.getProfileKey());
        if (authManagerProfileDao.getByKey(entityKey) == null) {
            return insert(authManagerProfile);
        }
        try {
            authManagerProfile.setUpdated(new Date());

            authManagerProfileDao.update(authManagerProfile);
            return BaseResult.success("属性更新成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    @Override
    public AuthManagerProfile getProfile(String userKey, String profileKey) {
        return this.getByKey(generateEntityKey(userKey, profileKey));
    }

    @Override
    public List<AuthManagerProfile> listProfiles(String userKey) {
        ManagerProfileSearcher managerProfileSearcher = new ManagerProfileSearcher();
        managerProfileSearcher.setUserKey(userKey);
        managerProfileSearcher.setAdvanced(false);
        return this.search(managerProfileSearcher);
    }

    private String generateEntityKey(String userKey, String profileKey) {
        return String.format("%s,%s", userKey, profileKey);
    }
}
