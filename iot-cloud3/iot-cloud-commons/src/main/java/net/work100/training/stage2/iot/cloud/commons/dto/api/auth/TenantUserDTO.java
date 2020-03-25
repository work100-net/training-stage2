package net.work100.training.stage2.iot.cloud.commons.dto.api.auth;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: TenantUserDTO</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-25 14:40
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-25   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public class TenantUserDTO implements Serializable {
    private String userKey;
    private String tenantCode;
    private String userName;
    private String password;
    /**
     * 状态：0=inactive, 1=active, 2=locked, 3=deleted
     */
    private int status;
    private boolean superuser;
    private String roles;
}
