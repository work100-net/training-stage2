package net.work100.training.stage2.iot.admin.dao.impl;

import net.work100.training.stage2.iot.admin.dao.UserDao;
import net.work100.training.stage2.iot.admin.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: UserDaoImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-example.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-13 13:23
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-13   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class UserDaoImpl implements UserDao {

    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    public User getUser(String loginId, String loginPwd) {
        logger.debug("调用方法 getUser(loginId:{}, loginPwd:{})", loginId, loginPwd);

        // 根据 loginId 查询出用户信息
        User user = getUserByLoginId(loginId);
        if (user != null) {
            // 验证 loginPwd 是否正确（区分大小写）
            if (user.getLoginPwd().equals(loginPwd)) {
                return user;
            }
        }
        return null;
    }


    /**
     * 获取模拟的用户数据
     *
     * @param loginId 登录ID
     * @return
     */
    private User getUserByLoginId(String loginId) {
        // 模拟 DB 存在的用户数据
        User dbUser = new User();
        dbUser.setUserName("Xiaojun");
        dbUser.setLoginId("admin");
        dbUser.setLoginPwd("admin");

        // 判断是否存在 loginId 的用户（忽略大小写）
        if (dbUser.getLoginId().equalsIgnoreCase(loginId)) {
            logger.info("匹配上用户：{}", dbUser);
            return dbUser;
        }
        logger.warn("未匹配任何用户，将返回 null");
        return null;
    }
}
