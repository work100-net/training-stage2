package net.work100.training.stage2.iot.cloud.web.console.api;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantDTO;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpClientUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.MapperUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: TenantApi</p>
 * <p>Description: 租户管理API</p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-console.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-25 13:16
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-25   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class TenantApi {

    /**
     * 获取租户信息
     *
     * @param apiTenantCode
     * @return
     */
    public static TenantDTO get(String apiTenantCode) {
        String url = String.format(API.API_AUTH_TENANT_GET, apiTenantCode);
        TenantDTO tenantDTO = null;
        try {
            String result = HttpClientUtils.doGet(url);
            BaseResult baseResult = MapperUtils.json2pojo(result, BaseResult.class);
            if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
                tenantDTO = MapperUtils.json2pojo(result, "data", TenantDTO.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenantDTO;
    }

    /**
     * 保存编辑
     *
     * @param apiTenantCode
     * @param tenantName
     * @param tenantDesc
     * @return
     */
    public static boolean save(String apiTenantCode, String tenantName, String tenantDesc) {
        String url = String.format(API.API_AUTH_TENANT_SAVE, apiTenantCode);
        boolean success = false;
        try {
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("tenantName", tenantName));
            params.add(new BasicNameValuePair("tenantDesc", tenantDesc));
            String result = HttpClientUtils.doPost(url, params);
            BaseResult baseResult = MapperUtils.json2pojo(result, BaseResult.class);
            if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
                success = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return success;
    }
}
