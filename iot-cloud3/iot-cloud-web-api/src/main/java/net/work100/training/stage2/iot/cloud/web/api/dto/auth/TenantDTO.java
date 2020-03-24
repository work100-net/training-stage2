package net.work100.training.stage2.iot.cloud.web.api.dto.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: TenantDTO</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-24 09:27
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-24   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public class TenantDTO implements Serializable {
    private Long id;
    private String tenantCode;
    private String tenantName;
    private String tenantDesc;
}
