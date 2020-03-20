package net.work100.training.stage2.iot.cloud.web.admin.service;

import net.work100.training.stage2.iot.cloud.commons.service.BaseService;
import net.work100.training.stage2.iot.cloud.domain.AuthManagerProfile;
import net.work100.training.stage2.iot.cloud.web.admin.dto.auth.ManagerProfileSearcher;

import java.util.List;

/**
 * <p>Title: AuthManagerProfileService</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-admin.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 23:01
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface AuthManagerProfileService extends BaseService<AuthManagerProfile, ManagerProfileSearcher> {

    /**
     * 删除属性
     *
     * @param userKey    用户Key
     * @param profileKey 属性Key
     */
    void deleteProfile(String userKey, String profileKey);

    /**
     * 删除某用户的所有属性
     *
     * @param userKey 用户Key
     */
    void deleteProfiles(String userKey);

    /**
     * 获取属性对象
     *
     * @param userKey    用户Key
     * @param profileKey 属性Key
     * @return
     */
    AuthManagerProfile getProfile(String userKey, String profileKey);

    /**
     * 获取属性列表
     *
     * @param userKey 用户Key
     * @return
     */
    List<AuthManagerProfile> listProfiles(String userKey);
}
