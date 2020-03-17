package net.work100.training.stage2.iot.cloud.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.util.Date;

/**
 * <p>Title: AuthManager</p>
 * <p>Description: 管理员账户表</p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 22:42
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public class AuthManager extends AbstractBaseDomain {

    private String userKey;

    @Length(min = 4, max = 20, message = "用户名必须介于 4~20 位之间")
    private String userName;

    @JsonIgnore
    @Length(min = 6, max = 20, message = "密码必须介于 6~20 位之间")
    private String password;

    /**
     * 状态：0=inactive, 1=active, 2=locked, 3=deleted
     */
    private int status;

    private boolean superuser;

    /**
     * 角色：admin, editor
     */
    @NotEmpty(message = "角色不能空")
    private String roles;

    private Date modifyPasswordTime;
}
