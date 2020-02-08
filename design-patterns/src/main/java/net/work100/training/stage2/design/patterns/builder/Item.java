package net.work100.training.stage2.design.patterns.builder;

/**
 * <p>Title: Item</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-builder-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 11:59
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface Item {
    /**
     * 命名
     *
     * @return
     */
    String name();

    /**
     * 打包
     *
     * @return
     */
    Packing packing();

    /**
     * 定价
     *
     * @return
     */
    float price();
}
