package net.work100.training.stage2.design.patterns.bridge;

/**
 * <p>Title: GreenCircle</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-bridge-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 14:27
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class GreenCircle implements DrawAPI {
    @Override
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius:" + radius + ", x:" + x + ", y:" + y + " ]");
    }
}
