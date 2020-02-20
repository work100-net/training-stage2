package net.work100.training.stage2.iot.admin.dao;

import net.work100.training.stage2.iot.admin.entity.User;

/**
 * <p>Title: UserDao</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-mvc.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-13 13:16
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-13   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface UserDao {
    /**
     * 根据ID及密码获取用户信息
     *
     * @param loginId  登录ID
     * @param loginPwd 登录密码
     * @return
     */
    User getUser(String loginId, String loginPwd);
}
