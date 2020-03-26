package net.work100.training.stage2.iot.cloud.commons.dto;

import lombok.Data;
import net.work100.training.stage2.iot.cloud.commons.utils.HttpUtils;

import java.io.Serializable;

/**
 * <p>Title: BaseResult</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-04 11:02
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-04   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public class BaseResult<T extends AbstractBaseDomain> implements Serializable {
    private int status;
    private String message;
    private Object data;
    private PageInfo<T> pageInfo;

    public static BaseResult success() {
        return createResult(HttpUtils.HTTP_STATUS_CODE_OK, "成功", null, null);
    }

    public static BaseResult success(String message) {
        return createResult(HttpUtils.HTTP_STATUS_CODE_OK, message, null, null);
    }

    public static BaseResult success(String message, Object data) {
        return createResult(HttpUtils.HTTP_STATUS_CODE_OK, message, data, null);
    }

    public static <T extends AbstractBaseDomain> BaseResult success(String message, PageInfo<T> pageInfo) {
        return createResult(HttpUtils.HTTP_STATUS_CODE_OK, message, null, pageInfo);
    }

    public static BaseResult fail() {
        return createResult(HttpUtils.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR, "失败", null, null);
    }

    public static BaseResult fail(String message) {
        return createResult(HttpUtils.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR, message, null, null);
    }

    public static BaseResult fail(int status, String message) {
        return createResult(status, message, null, null);
    }

    private static <T extends AbstractBaseDomain> BaseResult createResult(int status, String message, Object data, PageInfo<T> pageInfo) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setData(data);
        baseResult.setPageInfo(pageInfo);
        return baseResult;
    }
}
