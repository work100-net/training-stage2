package net.work100.training.stage2.iot.cloud.commons.service;

import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseResult;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseSearcher;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;

import java.util.List;

/**
 * <p>Title: BaseService</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-18 09:47
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-18   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface BaseService<T extends AbstractBaseDomain, Searcher extends BaseSearcher> {

    /**
     * 查询全部表记录
     *
     * @return
     */
    List<T> selectAll();

    /**
     * 新增
     *
     * @param entity
     * @return
     */
    BaseResult insert(T entity);

    /**
     * 更新
     *
     * @param entity
     * @return
     */
    BaseResult update(T entity);

    /**
     * 删除
     *
     * @param entityKey
     */
    void delete(String entityKey);

    /**
     * 批量删除
     *
     * @param entityKeys
     */
    void multiDelete(String[] entityKeys);

    /**
     * 获取单个对象
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * 获取对象
     *
     * @param entityKey
     * @return
     */
    T getByKey(String entityKey);

    /**
     * 搜索
     *
     * @param searcher 搜索器
     * @return
     */
    List<T> search(Searcher searcher);

    /**
     * 分页搜索
     *
     * @param draw
     * @param start    起始位置
     * @param length   每页长度
     * @param searcher 搜索器
     * @return
     */
    PageInfo<T> pageSearch(int draw, int start, int length, Searcher searcher);
}
