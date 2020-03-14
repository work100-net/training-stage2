package net.work100.training.stage2.iot.cloud.domain;

import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: AuthManager</p>
 * <p>Description: 管理员账户表</p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 22:42
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class AuthManager extends AbstractBaseDomain {

    private String userKey;
    private String userName;
    private String password;
    /**
     * 状态：0=inactive, 1=active, 2=locked, 3=deleted
     */
    private int status;
    private boolean superuser;
    /**
     * 角色：admin, editor
     */
    private String roles;
    private Date modifyPasswordTime;


    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuperuser() {
        return superuser;
    }

    public void setSuperuser(boolean superuser) {
        this.superuser = superuser;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public Date getModifyPasswordTime() {
        return modifyPasswordTime;
    }

    public void setModifyPasswordTime(Date modifyPasswordTime) {
        this.modifyPasswordTime = modifyPasswordTime;
    }

    @Override
    public String toString() {
        return "AuthManager{" +
                "userKey='" + userKey + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", superuser=" + superuser +
                ", roles='" + roles + '\'' +
                ", modifyPasswordTime=" + modifyPasswordTime +
                '}';
    }
}
