package net.work100.training.stage2.iot.cloud.commons.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dao.ApiBaseDao;
import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseSearcher;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.service.ApiBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>Title: BaseServiceImpl</p>
 * <p>Description: 通用的抽象Service实现</p>
 *
 * @author liuxiaojun
 * @date 2020-03-18 10:53
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-18   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class ApiAbstractBaseServiceImpl<T extends AbstractBaseDomain, Searcher extends BaseSearcher, D extends ApiBaseDao<T, Searcher>> implements ApiBaseService<T, Searcher> {

    @Autowired
    private D dao;

    @Override
    public List<T> selectAll(String apiTenantCode) {
        return dao.selectAll(apiTenantCode);
    }


    @Override
    public T getById(String apiTenantCode, Long id) {
        return dao.getById(apiTenantCode, id);
    }

    @Override
    public T getByKey(String apiTenantCode, String entityKey) {
        return dao.getByKey(apiTenantCode, entityKey);
    }


    @Override
    @Transactional(readOnly = false)
    public void delete(String apiTenantCode, String entityKey) {
        dao.delete(apiTenantCode, entityKey);
    }

    @Override
    @Transactional(readOnly = false)
    public void multiDelete(String apiTenantCode, String[] entityKeys) {
        dao.multiDelete(apiTenantCode, entityKeys);
    }

    @Override
    public List<T> search(String apiTenantCode, Searcher searcher) {
        return dao.search(apiTenantCode, searcher);
    }

    @Override
    public PageInfo<T> pageSearch(String apiTenantCode, int draw, Searcher searcher) {
        // 处理分页结果
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);

        // 获取记录数
        int recordsTotal = dao.count(apiTenantCode, searcher);
        pageInfo.setRecordsTotal(recordsTotal);
        pageInfo.setRecordsFiltered(recordsTotal);

        // 获取分页数据
        List<T> data = dao.pageSearch(apiTenantCode, searcher);
        pageInfo.setData(data);

        return pageInfo;
    }
}
