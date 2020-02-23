package net.work100.training.stage2.iot.cloud.web.admin.service.test;

import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
    private AuthManagerDao authManagerDao;

    @Test
    public void testSelectAll() {
        List<AuthManager> authManagers = authManagerDao.selectAll();
        for (AuthManager authManager : authManagers) {
            System.out.println("------------------------------------------------");
            System.out.println(authManager.toString());
        }
    }
}
