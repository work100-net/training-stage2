package net.work100.training.stage2.iot.cloud.web.admin.service.test;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.utils.DateTimeUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthManagerProfile;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerProfileService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: AuthManagerProfileServiceTest</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-spring-transaction.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 23:23
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml", "classpath:spring-context-druid.xml", "classpath:spring-context-mybatis.xml"})
@Transactional
@Rollback
public class AuthManagerProfileServiceTest {

    @Autowired
    private AuthManagerProfileService authManagerProfileService;

    @Test
    public void testSaveProfile() {
        String userKey = "8c41b9a54b2e2a4180cc1271b4672779";
        String profileKey = "login_error_time";
        String profileValue = DateTimeUtils.currentDate("yyyy-MM-dd HH:mm:ss");

        AuthManagerProfile authManagerProfile = new AuthManagerProfile();
        authManagerProfile.setUserKey(userKey);
        authManagerProfile.setProfileKey(profileKey);
        authManagerProfile.setProfileValue(profileValue);

        BaseResult success = authManagerProfileService.saveProfile(authManagerProfile);
        Assert.assertEquals(success.getStatus(), HttpUtils.HTTP_STATUS_CODE_OK);
    }

    @Test
    public void testSaveAndListProfiles() {
        String userKey = "8c41b9a54b2e2a4180cc1271b4672779";

        authManagerProfileService.deleteProfiles(userKey);

        AuthManagerProfile profile1 = new AuthManagerProfile();
        profile1.setUserKey(userKey);
        profile1.setProfileKey("login_error_time");
        profile1.setProfileValue(DateTimeUtils.currentDate("yyyy-MM-dd HH:mm:ss"));

        AuthManagerProfile profile2 = new AuthManagerProfile();
        profile2.setUserKey(userKey);
        profile2.setProfileKey("login_error_count");
        profile2.setProfileValue(Integer.toString(3));

        List<AuthManagerProfile> profiles = new ArrayList<>();
        profiles.add(profile1);
        profiles.add(profile2);

        authManagerProfileService.saveProfiles(profiles);

        List<AuthManagerProfile> list = authManagerProfileService.listProfiles(userKey);
        Assert.assertEquals(list.size(), 2);
    }
}
