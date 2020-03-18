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
public class BaseResult implements Serializable {
    private int status;
    private String message;

    public static BaseResult success() {
        return createResult(HttpUtils.HTTP_STATUS_CODE_OK, "成功");
    }

    public static BaseResult success(String message) {
        return createResult(HttpUtils.HTTP_STATUS_CODE_OK, message);
    }

    public static BaseResult fail() {
        return createResult(HttpUtils.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR, "失败");
    }

    public static BaseResult fail(String message) {
        return createResult(HttpUtils.HTTP_STATUS_CODE_INTERNAL_SERVER_ERROR, message);
    }

    public static BaseResult fail(int status, String message) {
        return createResult(status, message);
    }

    private static BaseResult createResult(int status, String message) {
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        return baseResult;
    }
}
