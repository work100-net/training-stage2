package net.work100.training.stage2.iot.cloud.web.admin.service;

import net.work100.training.stage2.iot.cloud.domain.AuthManager;

import java.util.List;

/**
 * <p>Title: AuthManagerService</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-mybatis.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 23:01
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface AuthManagerService {
    /**
     * 查询全部表记录
     *
     * @return
     */
    List<AuthManager> selectAll();
}
