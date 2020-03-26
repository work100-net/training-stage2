package net.work100.training.stage2.iot.cloud.web.console.api;

import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpClientUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.MapperUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: LoginApi</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-26 12:48
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-26   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class LoginApi {

    public static TenantUserDTO login(String apiTenantCode, String userName, String password){
        try {
            String url = String.format(API.API_AUTH_TENANT_LOGIN, apiTenantCode);
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("userName", userName));
            params.add(new BasicNameValuePair("password", password));
            String result = HttpClientUtils.doPost(url, params);
            int httpStatus = MapperUtils.json2pojo(result, "status", Integer.class);
            if (httpStatus == HttpUtils.HTTP_STATUS_CODE_OK) {
                return MapperUtils.json2pojo(result, "data", TenantUserDTO.class);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }
}
