package net.work100.training.stage2.design.patterns.facade;

/**
 * <p>Title: Circle</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-facade-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 15:10
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Circle::draw()");
    }
}
