package net.work100.training.stage2.iot.cloud.web.admin.service;

import net.work100.training.stage2.iot.cloud.domain.AuthManager;

import java.util.List;

/**
 * <p>Title: AuthManagerService</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
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

    /**
     * 新增
     *
     * @param authManager
     */
    void insert(AuthManager authManager);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 获取单个对象
     *
     * @param id
     * @return
     */
    AuthManager getById(Long id);


    /**
     * 更新
     *
     * @param authManager
     */
    void update(AuthManager authManager);


    /**
     * 模糊查询
     *
     * @param userName
     * @return
     */
    List<AuthManager> selectByName(String userName);

    /**
     * 登录验证
     *
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    AuthManager login(String userName, String password);
}
