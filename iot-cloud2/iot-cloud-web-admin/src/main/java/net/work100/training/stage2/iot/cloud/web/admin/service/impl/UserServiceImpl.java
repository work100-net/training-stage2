package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.domain.User;
import net.work100.training.stage2.iot.cloud.web.admin.dao.UserDao;
import net.work100.training.stage2.iot.cloud.web.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-mvc-maven-module.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-22 16:12
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-22   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User login(String loginId, String loginPwd) {
        return userDao.getUser(loginId, loginPwd);
    }
}
