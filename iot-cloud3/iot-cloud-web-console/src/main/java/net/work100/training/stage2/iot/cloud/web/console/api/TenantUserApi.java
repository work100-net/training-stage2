package net.work100.training.stage2.iot.cloud.web.console.api;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpClientUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.MapperUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: TenantUserApi</p>
 * <p>Description: 租户账户API</p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-console.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-25 13:16
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-25   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class TenantUserApi {

    public static TenantUserDTO login(String apiTenantCode, String userName, String password){
        String url = String.format(API.API_AUTH_TENANT_USER_LOGIN, apiTenantCode);
        TenantUserDTO tenantUserDTO = null;
        try {
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("userName", userName));
            params.add(new BasicNameValuePair("password", password));
            String result = HttpClientUtils.doPost(url, params);
            BaseResult baseResult = MapperUtils.json2pojo(result, BaseResult.class);
            if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
                tenantUserDTO = MapperUtils.json2pojo(result, "data", TenantUserDTO.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenantUserDTO;
    }
}
