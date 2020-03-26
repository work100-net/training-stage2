package net.work100.training.stage2.iot.cloud.web.api.web.controller.v1.auth;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.api.service.AuthTenantUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>Title: TenantUserController</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-api.html</p>
 *
 * @author liuxiaojun
 * @date 2020-03-26 12:02
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-26   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@RestController
@RequestMapping(value = "${api.path.v1}/auth/tenant/{apiTenantCode}/user")
public class TenantUserController {

}
