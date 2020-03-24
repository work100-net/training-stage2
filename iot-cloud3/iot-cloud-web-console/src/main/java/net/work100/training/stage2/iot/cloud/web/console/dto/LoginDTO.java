package net.work100.training.stage2.iot.cloud.web.console.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>Title: LoginDTO</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-24 14:18
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-24   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public class LoginDTO implements Serializable {
    private String tenantCode;
    private String userName;
    private String password;
    private boolean remember;
}
