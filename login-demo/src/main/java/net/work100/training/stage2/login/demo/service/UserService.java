package net.work100.training.stage2.login.demo.service;

/**
 * <p>Title: UserService</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-test.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-09 11:20
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-09   liuxiaojun     初始创建
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
    boolean login(String loginId, String loginPwd);
}
