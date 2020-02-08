package net.work100.training.stage2.design.patterns.bridge;

/**
 * <p>Title: DrawAPI</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-bridge-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:25
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface DrawAPI {
    /**
     * 画圆
     *
     * @param radius 半径
     * @param x
     * @param y
     */
    void drawCircle(int radius, int x, int y);
}
