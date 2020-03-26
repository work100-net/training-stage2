package net.work100.training.stage2.iot.cloud.commons.dao;

import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseSearcher;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>Title: ApiBaseDao</p>
 * <p>Description: 通用 DAO 接口</p>
 *
 * @author liuxiaojun
 * @date 2020-03-18 09:11
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-18   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface ApiBaseDao<T extends AbstractBaseDomain, Searcher extends BaseSearcher> {

    /**
     * 查询全部表记录
     *
     * @param apiTenantCode 租户编码
     * @return
     */
    List<T> selectAll(@Param("apiTenantCode") String apiTenantCode);


    /**
     * 新增
     *
     * @param apiTenantCode 租户编码
     * @param entity
     */
    void insert(@Param("apiTenantCode") String apiTenantCode, @Param("entity") T entity);

    /**
     * 更新
     *
     * @param apiTenantCode 租户编码
     * @param entity
     */
    void update(@Param("apiTenantCode") String apiTenantCode, @Param("entity") T entity);

    /**
     * 删除
     *
     * @param apiTenantCode 租户编码
     * @param entityKey
     */
    void delete(@Param("apiTenantCode") String apiTenantCode, @Param("entityKey") String entityKey);

    /**
     * 批量删除
     *
     * @param apiTenantCode 租户编码
     * @param entityKeys
     */
    void multiDelete(@Param("apiTenantCode") String apiTenantCode, @Param("entityKeys") String[] entityKeys);

    /**
     * 获取单个对象
     *
     * @param apiTenantCode 租户编码
     * @param id
     * @return
     */
    T getById(@Param("apiTenantCode") String apiTenantCode, @Param("id") Long id);

    /**
     * 获取单个对象
     *
     * @param apiTenantCode 租户编码
     * @param entityKey     实体对象Key
     * @return
     */
    T getByKey(@Param("apiTenantCode") String apiTenantCode, @Param("entityKey") String entityKey);

    /**
     * 搜索
     *
     * @param apiTenantCode 租户编码
     * @param searcher      搜索器
     * @return
     */
    List<T> search(@Param("apiTenantCode") String apiTenantCode, @Param("searcher") Searcher searcher);

    /**
     * 分页查询
     *
     * @param apiTenantCode 租户编码
     * @param searcher      搜索器
     * @return
     */
    List<T> pageSearch(@Param("apiTenantCode") String apiTenantCode, @Param("searcher") Searcher searcher);

    /**
     * 计数统计
     *
     * @param apiTenantCode 租户编码
     * @param searcher      搜索器
     * @return
     */
    int count(@Param("apiTenantCode") String apiTenantCode, @Param("searcher") Searcher searcher);
}
