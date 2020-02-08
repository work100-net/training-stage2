package net.work100.training.stage2.design.patterns.nullobject;

/**
 * <p>Title: AbstractCustomer</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-null-object-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:59
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class AbstractCustomer {
    protected String name;
    public abstract boolean isNil();
    public abstract String getName();
}
