package net.work100.training.stage2.iot.cloud.commons.dto.api.auth;

import lombok.Data;
import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;

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
public class TenantDTO extends AbstractBaseDomain {
    private Long id;
    private String tenantCode;
    private String tenantName;
    private String tenantDesc;
}
