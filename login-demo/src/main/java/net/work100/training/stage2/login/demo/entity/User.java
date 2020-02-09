package net.work100.training.stage2.login.demo.entity;

import java.io.Serializable;

/**
 * <p>Title: User</p>
 * <p>Description: 用户表</p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-test.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-09 10:43
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-09   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class User implements Serializable {
    private String userName;
    private String loginId;
    private String loginPwd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getLoginPwd() {
        return loginPwd;
    }

    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", loginId='" + loginId + '\'' +
                '}';
    }
}
