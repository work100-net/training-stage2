package net.work100.training.stage2.design.patterns.iterator;

/**
 * <p>Title: Container</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-iterator-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:19
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface Container {
    /**
     * 获取迭代器
     *
     * @return
     */
    Iterator getIterator();
}
