package net.work100.training.stage2.iot.cloud.web.api.dao;

import net.work100.training.stage2.iot.cloud.commons.dao.ApiBaseDao;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.api.dto.auth.TenantUserSearcher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>Title: AuthTenantUserDao</p>
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
public interface AuthTenantUserDao extends ApiBaseDao<AuthTenantUser, TenantUserSearcher> {

    /**
     * 获取账户对象
     *
     * @param apiTenantCode 租户编码
     * @param userName      用户名
     * @return
     */
    AuthTenantUser getByUserName(@Param("apiTenantCode") String apiTenantCode, @Param("userName") String userName);
}
