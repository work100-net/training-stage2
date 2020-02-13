package net.work100.training.stage2.iot.admin.service.impl;

import net.work100.training.stage2.iot.admin.commons.context.SpringContext;
import net.work100.training.stage2.iot.admin.dao.UserDao;
//import net.work100.training.stage2.iot.admin.dao.impl.UserDaoImpl;
import net.work100.training.stage2.iot.admin.entity.User;
import net.work100.training.stage2.iot.admin.service.UserService;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-example.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-13 13:26
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-13   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class UserServiceImpl implements UserService {

    private SpringContext context = new SpringContext();

//    private UserDao userDao = new UserDaoImpl();

    public User login(String loginId, String loginPwd) {
        UserDao userDao = (UserDao) context.getBean("userDao");
        return userDao.getUser(loginId, loginPwd);
    }
}
