package net.work100.training.stage2.iot.cloud.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>Title: AuthManager</p>
 * <p>Description: 管理员账户表</p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-mybatis.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 22:42
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class AuthManager implements Serializable {

    private Long id;
    private String userKey;
    private String userName;
    private String password;
    private int status;
    private boolean superuser;
    private String roles;
    private Date modifyPasswordTime;
    private Date created;
    private Date updated;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "AuthManager{" +
                "id=" + id +
                ", userKey='" + userKey + '\'' +
                ", userName='" + userName + '\'' +
                ", status=" + status +
                ", superuser=" + superuser +
                ", roles='" + roles + '\'' +
                ", modifyPasswordTime=" + modifyPasswordTime +
                ", created=" + created +
                ", updated=" + updated +
                '}';
    }
}
