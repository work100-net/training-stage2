package net.work100.training.stage2.iot.cloud.web.admin.dao;

import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>Title: AuthManagerDao</p>
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
public interface AuthManagerDao {

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
     * @param userKey
     */
    void delete(String userKey);

    /**
     * 批量删除
     *
     * @param userKeys
     */
    void multiDelete(String[] userKeys);

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
     * 获取账户对象
     *
     * @param userName 用户名
     * @return
     */
    AuthManager getByUserName(String userName);

    /**
     * 获取账户对象
     *
     * @param userKey 用户Key
     * @return
     */
    AuthManager getByUserKey(String userKey);

    /**
     * 搜索
     *
     * @param authManager 查询条件
     * @return
     */
    List<AuthManager> search(AuthManager authManager);

    /**
     * 分页查询
     *
     * @param params 查询条件及分页参数
     * @return
     */
    List<AuthManager> page(Map<String, Object> params);

    /**
     * 计数统计
     *
     * @param authManager 查询条件
     * @return
     */
    int count(AuthManager authManager);
}
