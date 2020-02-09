package net.work100.training.stage2.login.demo.service.impl;

import net.work100.training.stage2.login.demo.dao.UserDao;
import net.work100.training.stage2.login.demo.dao.impl.UserDaoImpl;
import net.work100.training.stage2.login.demo.service.UserService;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-test.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-09 11:22
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-09   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class UserServiceImpl implements UserService {

    // 数据访问层的具体实现
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean login(String loginId, String loginPwd) {
        return userDao.login(loginId, loginPwd) != null;
    }
}
