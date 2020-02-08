package net.work100.training.stage2.design.patterns.bridge;

/**
 * <p>Title: Shape</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-bridge-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:29
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    /**
     * 绘制
     */
    public abstract void draw();
}
