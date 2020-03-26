package net.work100.training.stage2.iot.cloud.web.console.test;

import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantDTO;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.web.console.api.LoginApi;
import net.work100.training.stage2.iot.cloud.web.console.api.TenantApi;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>Title: ApiTest</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-25 15:41
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-25   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class ApiTest {

    String apiTenantCode = "100001";

    @Test
    public void testAuthTenant() {
        // 获取租户信息
        TenantDTO tenantDTO = TenantApi.get(apiTenantCode);
        Assert.assertEquals(tenantDTO.getTenantCode(), apiTenantCode);
        System.out.println("get ok.");

        // 编辑保存租户信息
        boolean success = TenantApi.save(apiTenantCode, "租户名称1-a", "abc");
        Assert.assertTrue(success);
        System.out.println("save ok.");
    }

    @Test
    public void testLogin() {
        // 租户账户登录
        TenantUserDTO tenantUserDTO = LoginApi.login(apiTenantCode, "xiaojun.liu", "123456");
        Assert.assertNotNull(tenantUserDTO);
    }
}
