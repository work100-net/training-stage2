package net.work100.training.stage2.iot.cloud.domain;

import lombok.Data;
import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import org.hibernate.validator.constraints.Length;

/**
 * <p>Title: AuthTenant</p>
 * <p>Description: 租户表</p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-18 13:49
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-18   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public class AuthTenant extends AbstractBaseDomain {
    @Length(min = 6, max = 10, message = "租户编码必须介于 6~10 位之间")
    private String tenantCode;

    @Length(min = 4, max = 20, message = "租户名称必须介于 4~20 位之间")
    private String tenantName;

    @Length(max = 256, message = "租户编码必须小于 256 位")
    private String tenantDesc;
}
