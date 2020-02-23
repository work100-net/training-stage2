package net.work100.training.stage2.iot.cloud.web.admin.dao;

import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>Title: AuthManagerDao</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-mybatis.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 22:54
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Repository
public interface AuthManagerDao {

    /**
     * 查询全部表记录
     *
     * @return
     */
    List<AuthManager> selectAll();
}
