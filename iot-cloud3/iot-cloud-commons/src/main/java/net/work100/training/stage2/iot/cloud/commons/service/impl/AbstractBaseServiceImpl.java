package net.work100.training.stage2.iot.cloud.commons.service.impl;

import net.work100.training.stage2.iot.cloud.commons.dao.BaseDao;
import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;
import net.work100.training.stage2.iot.cloud.commons.dto.BaseSearcher;
import net.work100.training.stage2.iot.cloud.commons.dto.PageInfo;
import net.work100.training.stage2.iot.cloud.commons.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;

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
public abstract class AbstractBaseServiceImpl<T extends AbstractBaseDomain, Searcher extends BaseSearcher, D extends BaseDao<T, Searcher>> implements BaseService<T, Searcher> {

    @Autowired
    private D dao;

    @Override
    public List<T> selectAll() {
        return dao.selectAll();
    }


    @Override
    public T getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public T getByKey(String entityKey) {
        return dao.getByKey(entityKey);
    }


    @Override
    public void delete(String entityKey) {
        dao.delete(entityKey);
    }

    @Override
    public void multiDelete(String[] entityKeys) {
        dao.multiDelete(entityKeys);
    }

    @Override
    public List<T> search(Searcher searcher) {
        return dao.search(searcher);
    }

    @Override
    public PageInfo<T> pageSearch(int draw, Searcher searcher) {
        // 处理分页结果
        PageInfo<T> pageInfo = new PageInfo<>();
        pageInfo.setDraw(draw);

        // 获取记录数
        int recordsTotal = dao.count(searcher);
        pageInfo.setRecordsTotal(recordsTotal);
        pageInfo.setRecordsFiltered(recordsTotal);

        // 获取分页数据
        List<T> data = dao.pageSearch(searcher);
        pageInfo.setData(data);

        return pageInfo;
    }
}
