package net.work100.training.stage2.iot.cloud.commons.service;

import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseSearcher;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;

import java.util.List;

/**
 * <p>Title: ApiBaseService</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-18 09:47
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-18   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface ApiBaseService<T extends AbstractBaseDomain, Searcher extends BaseSearcher> {

    /**
     * 查询全部表记录
     *
     * @param apiTenantCode 租户编码
     * @return
     */
    List<T> selectAll(String apiTenantCode);

    /**
     * 新增
     *
     * @param apiTenantCode 租户编码
     * @param entity
     * @return
     */
    BaseResult insert(String apiTenantCode, T entity);

    /**
     * 更新
     *
     * @param apiTenantCode 租户编码
     * @param entity
     * @return
     */
    BaseResult update(String apiTenantCode, T entity);

    /**
     * 删除
     *
     * @param apiTenantCode 租户编码
     * @param entityKey
     */
    void delete(String apiTenantCode, String entityKey);

    /**
     * 批量删除
     *
     * @param apiTenantCode 租户编码
     * @param entityKeys
     */
    void multiDelete(String apiTenantCode, String[] entityKeys);

    /**
     * 获取单个对象
     *
     * @param apiTenantCode 租户编码
     * @param id
     * @return
     */
    T getById(String apiTenantCode, Long id);

    /**
     * 获取对象
     *
     * @param apiTenantCode 租户编码
     * @param entityKey
     * @return
     */
    T getByKey(String apiTenantCode, String entityKey);

    /**
     * 搜索
     *
     * @param apiTenantCode 租户编码
     * @param searcher      搜索器
     * @return
     */
    List<T> search(String apiTenantCode, Searcher searcher);

    /**
     * 分页搜索
     *
     * @param apiTenantCode 租户编码
     * @param draw
     * @param searcher      搜索器
     * @return
     */
    PageInfo<T> pageSearch(String apiTenantCode, int draw, Searcher searcher);
}
