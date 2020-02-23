package net.work100.training.stage2.iot.cloud.web.admin.service;

import net.work100.training.stage2.iot.cloud.domain.User;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-mvc-maven-module.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-22 16:11
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-22   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface UserService {
    /**
     * 登录验证
     *
     * @param loginId  登录ID
     * @param loginPwd 登录密码
     * @return
     */
    User login(String loginId, String loginPwd);
}
