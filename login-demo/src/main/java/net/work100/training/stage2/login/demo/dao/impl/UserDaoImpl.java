package net.work100.training.stage2.login.demo.dao.impl;

import net.work100.training.stage2.login.demo.dao.UserDao;
import net.work100.training.stage2.login.demo.entity.User;

/**
 * <p>Title: UserDaoImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-test.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-09 11:04
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-09   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class UserDaoImpl implements UserDao {

    @Override
    public User login(String loginId, String loginPwd) {
        // 根据 loginId 查询出用户信息
        User user = getUserByLoginId(loginId);
        if (user!=null) {
            // 验证 loginPwd 是否正确（区分大小写）
            if(user.getLoginPwd().equals(loginPwd)){
                return user;
            }
        }
        return null;
    }


    private User getUserByLoginId(String loginId){
        // 模拟 DB 存在的用户数据
        User dbUser = new User();
        dbUser.setUserName("Xiaojun");
        dbUser.setLoginId("admin");
        dbUser.setLoginPwd("admin");

        // 判断是否存在 loginId 的用户（忽略大小写）
        if(dbUser.getLoginId().equalsIgnoreCase(loginId)){
            return dbUser;
        }
        return null;
    }
}
