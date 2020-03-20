package net.work100.training.stage2.iot.cloud.web.admin.dto.auth;

import lombok.Data;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseSearcher;

/**
 * <p>Title: ManagerProfileSearcher</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-08 17:02
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Data
public class ManagerProfileSearcher extends BaseSearcher {
    private String userKey;
    private String profileKey;
    private String profileValue;
}
