package net.work100.training.stage2.iot.cloud.domain;

import lombok.Data;
import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

/**
 * <p>Title: AuthManagerProfile</p>
 * <p>Description: 管理员账户属性表</p>
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
public class AuthManagerProfile extends AbstractBaseDomain {
    @NotEmpty(message = "用户Key不能空")
    private String userKey;

    @Length(min = 2, max = 50, message = "属性Key必须介于 2~20 位之间")
    private String profileKey;

    @Length(max = 100, message = "属性值必须小于 100 位")
    private String profileValue;
}
