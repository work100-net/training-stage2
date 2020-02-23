package net.work100.training.stage2.iot.cloud.web.admin.service.impl;

import net.work100.training.stage2.iot.cloud.domain.AuthManager;
import net.work100.training.stage2.iot.cloud.web.admin.dao.AuthManagerDao;
import net.work100.training.stage2.iot.cloud.web.admin.service.AuthManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>Title: AuthManagerServiceImpl</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-mybatis.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-23 23:06
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-23   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Service
public class AuthManagerServiceImpl implements AuthManagerService {

    @Autowired
    private AuthManagerDao authManagerDao;

    @Override
    public List<AuthManager> selectAll() {
        return authManagerDao.selectAll();
    }
}
