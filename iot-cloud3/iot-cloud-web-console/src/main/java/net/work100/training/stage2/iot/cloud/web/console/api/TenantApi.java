package net.work100.training.stage2.iot.cloud.web.console.api;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantDTO;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpClientUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;
import net.work100.training.stage2.iot.cloud.commons.utils.MapperUtils;

/**
 * <p>Title: TenantApi</p>
 * <p>Description: 租户管理API</p>
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
     * @param tenantCode
     * @return
     */
    public static TenantDTO get(String tenantCode) {
        TenantDTO tenantDTO = null;
        try {
            String result = HttpClientUtils.doGet(API.API_AUTH_TENANT_GET + tenantCode);
            BaseResult baseResult = MapperUtils.json2pojo(result, BaseResult.class);
            if (baseResult.getStatus() == HttpUtils.HTTP_STATUS_CODE_OK) {
                tenantDTO = MapperUtils.json2pojo(result, "data", TenantDTO.class);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tenantDTO;
    }
}
