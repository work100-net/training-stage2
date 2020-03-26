package net.work100.training.stage2.iot.cloud.web.console.test;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantDTO;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.web.console.api.LoginApi;
import net.work100.training.stage2.iot.cloud.web.console.api.TenantApi;
import net.work100.training.stage2.iot.cloud.web.console.api.TenantUserApi;
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

    @Test
    public void testAuthTenantUser() {
        // 租户账户分页搜索
        PageInfo<TenantUserDTO> pageInfo = TenantUserApi.pageSearch(apiTenantCode, 1, 0, 10, false, "", "", "", -1);
        Assert.assertNotNull(pageInfo);

        // 新增
        BaseResult addResult = TenantUserApi.add(apiTenantCode, "testaaabbbccc", "123456", 1, false, "editor");
        Assert.assertTrue(addResult.getMessage(), addResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK);

        // 租户账户登录
        TenantUserDTO tenantUserDTO = LoginApi.login(apiTenantCode, "testaaabbbccc", "123456");
        String userKey = tenantUserDTO.getUserKey();

        // 获取
        TenantUserDTO tenantUserDTO1 = TenantUserApi.get(apiTenantCode, userKey);
        Assert.assertEquals(userKey, tenantUserDTO.getUserKey());

        // 编辑
        BaseResult editResult = TenantUserApi.edit(apiTenantCode, userKey, 0, false, "editor");
        Assert.assertTrue(editResult.getMessage(), editResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK);

        // 删除
        BaseResult deleteResult = TenantUserApi.delete(apiTenantCode, userKey);
        Assert.assertTrue(deleteResult.getMessage(), deleteResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK);

        // 批量删除
        TenantUserApi.add(apiTenantCode, "testaaabbbccc1", "123456", 1, false, "editor");
        TenantUserApi.add(apiTenantCode, "testaaabbbccc2", "123456", 1, false, "editor");
        TenantUserDTO login1 = LoginApi.login(apiTenantCode, "testaaabbbccc1", "123456");
        TenantUserDTO login2 = LoginApi.login(apiTenantCode, "testaaabbbccc2", "123456");

        BaseResult multiDeleteResult = TenantUserApi.multiDelete(apiTenantCode, new String[]{login1.getUserKey(), login2.getUserKey()});
        Assert.assertTrue(multiDeleteResult.getMessage(), multiDeleteResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK);
    }
}
