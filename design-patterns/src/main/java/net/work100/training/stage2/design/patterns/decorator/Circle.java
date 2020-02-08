package net.work100.training.stage2.design.patterns.decorator;

/**
 * <p>Title: Circle</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-decorator-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 15:01
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Shape: Circle");
    }
}
