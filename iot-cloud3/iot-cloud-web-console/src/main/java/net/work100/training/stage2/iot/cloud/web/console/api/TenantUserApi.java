package net.work100.training.stage2.iot.cloud.web.console.api;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
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

    /**
     * 分页搜索
     *
     * @param apiTenantCode
     * @param draw
     * @param start
     * @param length
     * @param advanced
     * @param keyword
     * @param userName
     * @param roles
     * @param status
     * @return
     */
    public static PageInfo<TenantUserDTO> pageSearch(
            String apiTenantCode,
            int draw,
            int start,
            int length,
            boolean advanced,
            String keyword,
            String userName,
            String roles,
            int status) {
        try {
            String url = String.format(API.API_AUTH_TENANT_USER_PAGE_SEARCH, apiTenantCode);
            PageInfo<TenantUserDTO> pageInfo = null;
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("draw", Integer.toString(draw)));
            params.add(new BasicNameValuePair("start", Integer.toString(start)));
            params.add(new BasicNameValuePair("length", Integer.toString(length)));
            params.add(new BasicNameValuePair("advanced", Boolean.toString(advanced)));
            params.add(new BasicNameValuePair("keyword", keyword));
            params.add(new BasicNameValuePair("userName", userName));
            params.add(new BasicNameValuePair("roles", roles));
            params.add(new BasicNameValuePair("status", Integer.toString(status)));
            String result = HttpClientUtils.doPost(url, params);
            int httpStatus = MapperUtils.json2pojo(result, "status", Integer.class);
            if (httpStatus == HttpUtils.HTTP_STATUS_CODE_OK) {
                pageInfo = new PageInfo<>();
                pageInfo.setDraw(MapperUtils.json2pojo(result, "pageInfo/draw", Integer.class));
                pageInfo.setRecordsTotal(MapperUtils.json2pojo(result, "pageInfo/recordsTotal", Integer.class));
                pageInfo.setRecordsFiltered(MapperUtils.json2pojo(result, "pageInfo/recordsFiltered", Integer.class));
                pageInfo.setData(MapperUtils.json2list(result, "pageInfo/data", TenantUserDTO.class));
            }
            return pageInfo;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 获取账户
     *
     * @param apiTenantCode
     * @param userKey
     * @return
     */
    public static TenantUserDTO get(String apiTenantCode, String userKey) {
        try {
            String url = String.format(API.API_AUTH_TENANT_USER_GET, apiTenantCode, userKey);
            String result = HttpClientUtils.doGet(url);
            int httpStatus = MapperUtils.json2pojo(result, "status", Integer.class);
            if (httpStatus == HttpUtils.HTTP_STATUS_CODE_OK) {
                return MapperUtils.json2pojo(result, "data", TenantUserDTO.class);
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 新增账户
     *
     * @param apiTenantCode
     * @param userName
     * @param password
     * @param status
     * @param superuser
     * @param roles
     * @return
     */
    public static BaseResult add(String apiTenantCode, String userName, String password, int status, boolean superuser, String roles) {
        try {
            String url = String.format(API.API_AUTH_TENANT_USER_ADD, apiTenantCode);
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("userName", userName));
            params.add(new BasicNameValuePair("password", password));
            params.add(new BasicNameValuePair("status", Integer.toString(status)));
            params.add(new BasicNameValuePair("superuser", Boolean.toString(superuser)));
            params.add(new BasicNameValuePair("roles", roles));
            String result = HttpClientUtils.doPost(url, params);
            int httpStatus = MapperUtils.json2pojo(result, "status", Integer.class);
            if (httpStatus != HttpUtils.HTTP_STATUS_CODE_OK) {
                return BaseResult.fail(MapperUtils.json2pojo(result, "message", String.class));
            }
            return BaseResult.success("新增账户成功");
        } catch (Exception e) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 编辑账户
     *
     * @param apiTenantCode
     * @param userKey
     * @param status
     * @param superuser
     * @param roles
     * @return
     */
    public static BaseResult edit(String apiTenantCode, String userKey, int status, boolean superuser, String roles) {
        try {
            String url = String.format(API.API_AUTH_TENANT_USER_EDIT, apiTenantCode, userKey);
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("status", Integer.toString(status)));
            params.add(new BasicNameValuePair("superuser", Boolean.toString(superuser)));
            params.add(new BasicNameValuePair("roles", roles));
            String result = HttpClientUtils.doPost(url, params);
            int httpStatus = MapperUtils.json2pojo(result, "status", Integer.class);
            if (httpStatus != HttpUtils.HTTP_STATUS_CODE_OK) {
                return BaseResult.fail(MapperUtils.json2pojo(result, "message", String.class));
            }
            return BaseResult.success("编辑账户成功");
        } catch (Exception e) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 删除账户
     *
     * @param apiTenantCode
     * @param userKey
     * @return
     */
    public static BaseResult delete(String apiTenantCode, String userKey) {
        try {
            String url = String.format(API.API_AUTH_TENANT_USER_DELETE, apiTenantCode, userKey);
            String result = HttpClientUtils.doGet(url);
            int httpStatus = MapperUtils.json2pojo(result, "status", Integer.class);
            if (httpStatus == HttpUtils.HTTP_STATUS_CODE_OK) {
                return BaseResult.success("删除成功");
            }
            return BaseResult.fail("删除失败");
        } catch (Exception e) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 批量删除账户
     *
     * @param apiTenantCode
     * @param userKeys
     * @return
     */
    public static BaseResult multiDelete(String apiTenantCode, String[] userKeys) {
        try {
            String url = String.format(API.API_AUTH_TENANT_USER_MULTI_DELETE, apiTenantCode);
            List<BasicNameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("userKeys", String.join(",", userKeys)));
            String result = HttpClientUtils.doPost(url, params);
            int httpStatus = MapperUtils.json2pojo(result, "status", Integer.class);
            if (httpStatus == HttpUtils.HTTP_STATUS_CODE_OK) {
                return BaseResult.success("删除成功");
            }
            return BaseResult.fail("删除失败");
        } catch (Exception e) {
            return BaseResult.fail("未知错误");
        }
    }
}
