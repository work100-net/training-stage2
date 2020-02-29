package net.work100.training.stage2.iot.cloud.web.admin.service.test;

import net.work100.training.stage2.iot.cloud.commons.utils.EncryptionUtils;
import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * <p>Title: AuthManagerServiceTest</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-mybatis.html</p>
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
public class AuthManagerServiceTest {

    @Autowired
    private AuthManagerService authManagerService;

    @Test
    public void testSelectAll() {
        List<AuthManager> authManagers = authManagerService.selectAll();
        for (AuthManager authManager : authManagers) {
            System.out.println("------------------------------------------------");
            System.out.println(authManager.toString());
        }
    }

    @Test
    public void testInsert() {
        String userName = "xiaojun_" + RandomStringUtils.randomAlphanumeric(4);
        String password = "123456";

        AuthManager authManager = new AuthManager();
        authManager.setUserKey(EncryptionUtils.encryptText(EncryptionUtils.EncryptionType.MD5, userName));
        authManager.setUserName(userName);
        authManager.setPassword(EncryptionUtils.encryptPassword(EncryptionUtils.EncryptionType.MD5, password));
        authManager.setStatus(0);
        authManager.setSuperuser(false);
        authManager.setRoles("editor");
        authManager.setCreated(new Date());
        authManager.setUpdated(new Date());
        authManagerService.insert(authManager);
    }

    @Test
    public void testDelete() {
        authManagerService.delete(6L);
    }

    @Test
    public void testGetById() {
        AuthManager authManager = authManagerService.getById(1L);
        System.out.println("---------------------------------");
        System.out.println(authManager.toString());
        System.out.println("---------------------------------");
    }

    @Test
    public void testUpdate() {
        AuthManager authManager = authManagerService.getById(1L);
        System.out.println("---------------------------------");
        System.out.println("修改前: " + authManager.getModifyPasswordTime());

        authManager.setModifyPasswordTime(new Date());
        authManager.setUpdated(new Date());
        authManagerService.update(authManager);

        System.out.println("修改后: " + authManager.getModifyPasswordTime());
        System.out.println("---------------------------------");
    }

    @Test
    public void testSelectByName() {
        String userName = "xiaojun";
        List<AuthManager> authManagers = authManagerService.selectByName(userName);
        for (AuthManager authManager : authManagers) {
            System.out.println("------------------------------------------------");
            System.out.println(authManager.toString());
        }
    }

    @Test
    public void testLogin()
    {
        String userName = "xiaojun.liu";
        String password = "123456";
        AuthManager authManager = authManagerService.login(userName, password);
        Assert.assertNotNull(authManager);
    }
}
