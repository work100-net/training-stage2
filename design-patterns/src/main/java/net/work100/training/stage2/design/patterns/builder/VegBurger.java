package net.work100.training.stage2.design.patterns.builder;

/**
 * <p>Title: VegBurger</p>
 * <p>Description: 蔬菜汉堡</p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-builder-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 12:08
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class VegBurger extends Burger {
    @Override
    public float price() {
        return 25.0f;
    }

    @Override
    public String name() {
        return "Veg Burger";
    }
}
