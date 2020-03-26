package net.work100.training.stage2.iot.cloud.web.api.web.controller.v1.auth;

import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.dto.api.auth.TenantUserDTO;
import net.work100.training.stage2.iot.cloud.domain.AuthTenantUser;
import net.work100.training.stage2.iot.cloud.web.api.dto.auth.TenantUserSearcher;
import net.work100.training.stage2.iot.cloud.web.api.service.AuthTenantUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private AuthTenantUserService authTenantUserService;

    /**
     * 分页搜索
     *
     * @param apiTenantCode
     * @param draw
     * @param start
     * @param length
     * @param advanced
     * @param keyword
     * @param userName
     * @param roles
     * @param status
     * @return
     */
    @RequestMapping(value = "page-search", method = RequestMethod.POST)
    public BaseResult pageSearch(@PathVariable(required = true) String apiTenantCode,
                                 @RequestParam(required = true) String draw,
                                 @RequestParam(required = true) String start,
                                 @RequestParam(required = true) String length,
                                 @RequestParam(required = true) String advanced,
                                 @RequestParam(required = false) String keyword,
                                 @RequestParam(required = false) String userName,
                                 @RequestParam(required = false) String roles,
                                 @RequestParam(required = true) String status) {
        try {

            // 参数处理
            int intDraw = StringUtils.isBlank(draw) ? 0 : Integer.parseInt(draw);
            int intStart = StringUtils.isBlank(start) ? 0 : Integer.parseInt(start);
            int intLength = StringUtils.isBlank(length) ? 0 : Integer.parseInt(length);
            boolean boolAdvanced = StringUtils.isBlank(advanced) ? false : Boolean.parseBoolean(advanced);
            int intStatus = StringUtils.isBlank(status) ? -1 : Integer.parseInt(status);

            TenantUserSearcher tenantUserSearcher = new TenantUserSearcher();
            tenantUserSearcher.setAdvanced(boolAdvanced);
            if (!boolAdvanced) {
                tenantUserSearcher.setKeyword(keyword);

            } else {
                tenantUserSearcher.setUserName(userName);
                tenantUserSearcher.setRoles(roles);
                tenantUserSearcher.setStatus(intStatus);
            }

            tenantUserSearcher.setStart(intStart);
            tenantUserSearcher.setLength(intLength);

            PageInfo<AuthTenantUser> authTenantUserPageInfo = authTenantUserService.pageSearch(apiTenantCode, intDraw, tenantUserSearcher);
            List<TenantUserDTO> data = null;
            if (authTenantUserPageInfo != null && authTenantUserPageInfo.getData().size() > 0) {
                data = new ArrayList<>();
                for (AuthTenantUser authTenantUser : authTenantUserPageInfo.getData()) {
                    TenantUserDTO tenantUserDTO = new TenantUserDTO();
                    BeanUtils.copyProperties(authTenantUser, tenantUserDTO);
                    data.add(tenantUserDTO);
                }
            }
            PageInfo<TenantUserDTO> pageInfo = new PageInfo<>();
            BeanUtils.copyProperties(authTenantUserPageInfo, pageInfo);
            pageInfo.setData(data);
            return BaseResult.success("成功", pageInfo);
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 获取账户
     *
     * @param apiTenantCode
     * @param userKey
     * @return
     */
    @RequestMapping(value = "{userKey}", method = RequestMethod.GET)
    public BaseResult get(@PathVariable(required = true) String apiTenantCode,
                          @PathVariable(required = true) String userKey) {
        try {
            AuthTenantUser authTenantUser = authTenantUserService.getByKey(apiTenantCode, userKey);
            if (authTenantUser != null) {
                TenantUserDTO tenantUserDTO = new TenantUserDTO();
                BeanUtils.copyProperties(authTenantUser, tenantUserDTO);
                BaseResult.success("成功", tenantUserDTO);
            }
            return BaseResult.fail("未找到相关数据");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 新增账户
     *
     * @param apiTenantCode
     * @param userName
     * @param password
     * @param status
     * @param superuser
     * @param roles
     * @return
     */
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public BaseResult add(@PathVariable(required = true) String apiTenantCode,
                          @RequestParam(required = true) String userName,
                          @RequestParam(required = true) String password,
                          @RequestParam(required = true) String status,
                          @RequestParam(required = true) String superuser,
                          @RequestParam(required = true) String roles) {
        try {

            // 参数处理
            int intStatus = StringUtils.isBlank(status) ? 0 : Integer.parseInt(status);
            boolean isSuperuser = StringUtils.isBlank(superuser) ? false : Boolean.parseBoolean(superuser);

            AuthTenantUser authTenantUser = new AuthTenantUser();
            authTenantUser.setTenantCode(apiTenantCode);
            authTenantUser.setUserName(userName);
            authTenantUser.setPassword(password);
            authTenantUser.setStatus(intStatus);
            authTenantUser.setSuperuser(isSuperuser);
            authTenantUser.setRoles(roles);

            return authTenantUserService.insert(apiTenantCode, authTenantUser);
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 新增账户
     *
     * @param apiTenantCode
     * @param userKey
     * @param status
     * @param superuser
     * @param roles
     * @return
     */
    @RequestMapping(value = "edit/{userKey}", method = RequestMethod.POST)
    public BaseResult edit(@PathVariable(required = true) String apiTenantCode,
                           @PathVariable(required = true) String userKey,
                           @RequestParam(required = true) String status,
                           @RequestParam(required = true) String superuser,
                           @RequestParam(required = true) String roles) {
        try {
            // 参数处理
            int intStatus = StringUtils.isBlank(status) ? 0 : Integer.parseInt(status);
            boolean isSuperuser = StringUtils.isBlank(superuser) ? false : Boolean.parseBoolean(superuser);

            AuthTenantUser authTenantUser = new AuthTenantUser();
            authTenantUser.setTenantCode(apiTenantCode);
            authTenantUser.setUserKey(userKey);
            authTenantUser.setStatus(intStatus);
            authTenantUser.setSuperuser(isSuperuser);
            authTenantUser.setRoles(roles);

            return authTenantUserService.update(apiTenantCode, authTenantUser);
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }

    /**
     * 删除账户
     *
     * @param apiTenantCode
     * @param userKey
     * @return
     */
    @RequestMapping(value = "delete/{userKey}", method = RequestMethod.GET)
    public BaseResult delete(@PathVariable(required = true) String apiTenantCode,
                             @PathVariable(required = true) String userKey) {
        try {
            authTenantUserService.delete(apiTenantCode, userKey);
            return BaseResult.success("删除成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }



    /**
     * 批量删除账户
     *
     * @param apiTenantCode
     * @param userKeys 多个用,分隔
     * @return
     */
    @RequestMapping(value = "multi-delete", method = RequestMethod.POST)
    public BaseResult multiDelete(@PathVariable(required = true) String apiTenantCode,
                                  @RequestParam(required = true) String userKeys) {
        try {
            String[] arrUserKeys = userKeys.split(",");
            if (arrUserKeys == null || arrUserKeys.length <= 0) {
                return BaseResult.fail("请至少选择一条记录");
            }
            authTenantUserService.multiDelete(apiTenantCode, arrUserKeys);
            return BaseResult.success("批量删除成功");
        } catch (Exception ex) {
            return BaseResult.fail("未知错误");
        }
    }
}
