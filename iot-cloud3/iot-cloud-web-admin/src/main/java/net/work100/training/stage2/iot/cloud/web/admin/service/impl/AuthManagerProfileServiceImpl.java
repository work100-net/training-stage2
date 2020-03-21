package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.service.impl.AbstractBaseServiceImpl;
import net.work100.training.stage2.iot.cloud.domain.AuthManagerProfile;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerProfileDao;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerProfileSearcher;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
@Transactional(readOnly = true)
public class AuthManagerProfileServiceImpl extends AbstractBaseServiceImpl<AuthManagerProfile, ManagerProfileSearcher, AuthManagerProfileDao> implements AuthManagerProfileService {

    @Autowired
    private AuthManagerProfileDao authManagerProfileDao;

    @Override
    @Transactional(readOnly = false)
    public BaseResult saveProfile(AuthManagerProfile profile) {
        if (this.getProfile(profile.getUserKey(), profile.getProfileKey()) == null) {
            return this.insert(profile);
        } else {
            return this.update(profile);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void saveProfiles(List<AuthManagerProfile> profiles) {
        for (AuthManagerProfile profile : profiles) {
            this.saveProfile(profile);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProfile(String userKey, String profileKey) {
        this.delete(generateEntityKey(userKey, profileKey));
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteProfiles(String userKey) {
        this.multiDelete(new String[]{userKey});
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult insert(AuthManagerProfile authManagerProfile) {
        authManagerProfile.setCreated(new Date());
        authManagerProfile.setUpdated(new Date());

        authManagerProfileDao.insert(authManagerProfile);
        return BaseResult.success("新增属性成功");
    }

    @Override
    @Transactional(readOnly = false)
    public BaseResult update(AuthManagerProfile authManagerProfile) {
        authManagerProfile.setUpdated(new Date());

        authManagerProfileDao.update(authManagerProfile);
        return BaseResult.success("属性更新成功");
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
