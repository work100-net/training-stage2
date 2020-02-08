package net.work100.training.stage2.design.patterns.filter;

import java.util.List;

/**
 * <p>Title: Criteria</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-filter-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:41
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface Criteria {
    /**
     * 过滤符合标准的 Person
     *
     * @param persons Person 集合
     * @return
     */
    List<Person> meetCriteria(List<Person> persons);
}
