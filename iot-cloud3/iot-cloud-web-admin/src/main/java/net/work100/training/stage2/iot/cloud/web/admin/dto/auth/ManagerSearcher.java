package net.work100.training.stage2.iot.cloud.web.admin.dto.auth;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseSearcher;

/**
 * <p>Title: ManagerSearcher</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-08 17:02
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class ManagerSearcher extends BaseSearcher {

    private String userName;
    private String roles;
    private int status;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
