package net.work100.training.stage2.design.patterns.iterator;

/**
 * <p>Title: Iterator</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-iterator-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:18
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface Iterator {
    /**
     * 是否有下一个
     *
     * @return
     */
    boolean hasNext();

    /**
     * 下一个
     *
     * @return
     */
    Object next();
}
