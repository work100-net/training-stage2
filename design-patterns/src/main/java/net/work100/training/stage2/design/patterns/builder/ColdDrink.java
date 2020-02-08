package net.work100.training.stage2.design.patterns.builder;

/**
 * <p>Title: ColdDrink</p>
 * <p>Description: 冷饮</p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-builder-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 12:04
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class ColdDrink implements Item {

    @Override
    public Packing packing() {
        return new Bottle();
    }

    /**
     * 定价
     *
     * @return
     */
    @Override
    public abstract float price();
}
