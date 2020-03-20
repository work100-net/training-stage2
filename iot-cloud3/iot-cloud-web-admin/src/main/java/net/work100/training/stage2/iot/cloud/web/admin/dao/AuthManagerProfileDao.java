package net.work100.training.stage2.iot.cloud.web.admin.dao;

import net.work100.training.stage2.iot.cloud.commons.dao.BaseDao;
import net.work100.training.stage2.iot.cloud.domain.AuthManagerProfile;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerProfileSearcher;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: AuthManagerProfileDao</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 22:54
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Repository
public interface AuthManagerProfileDao extends BaseDao<AuthManagerProfile, ManagerProfileSearcher> {

}
