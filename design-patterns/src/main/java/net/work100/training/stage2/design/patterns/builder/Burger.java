package net.work100.training.stage2.design.patterns.builder;

/**
 * <p>Title: Burger</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-builder-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 12:07
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    /**
     * 定价
     *
     * @return
     */
    @Override
    public abstract float price();
}
