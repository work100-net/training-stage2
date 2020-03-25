package net.work100.training.stage2.iot.cloud.web.console.test;

import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantDTO;
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

    @Test
    public void testAuthTenant() {
        TenantDTO tenantDTO = TenantApi.get("100001");
        Assert.assertEquals(tenantDTO.getTenantCode(), "100001");
    }
}
