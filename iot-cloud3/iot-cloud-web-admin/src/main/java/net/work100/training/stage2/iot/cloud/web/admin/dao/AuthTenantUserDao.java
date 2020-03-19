package net.work100.training.stage2.iot.cloud.web.admin.dao;

import net.work100.training.stage2.iot.cloud.commons.dao.BaseDao;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.TenantUserSearcher;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

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
public interface AuthTenantUserDao extends BaseDao<AuthTenantUser, TenantUserSearcher> {

    /**
     * 查询全部表记录
     *
     * @param tenantCode 租户编码
     * @return
     */
    List<AuthTenantUser> selectAll(String tenantCode);

    /**
     * 获取账户对象
     *
     * @param tenantCode 租户编码
     * @param userName   用户名
     * @return
     */
    AuthTenantUser getByUserName(@Param("tenantCode") String tenantCode, @Param("userName") String userName);
}
