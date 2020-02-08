package net.work100.training.stage2.design.patterns.observer;

/**
 * <p>Title: Observer</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-observer-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:37
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class Observer {
    protected Subject subject;

    /**
     * 更新
     */
    public abstract void update();
}
