package net.work100.training.stage2.login.demo.dao;

import net.work100.training.stage2.login.demo.entity.User;

/**
 * <p>Title: UserDao</p>
 * <p>Description: 用户数据访问</p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-test.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-09 10:49
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-09   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface UserDao {
    /**
     * 登录验证
     *
     * @param loginId  登录ID
     * @param loginPwd 登录密码
     * @return
     */
    User login(String loginId, String loginPwd);
}
