package net.work100.training.stage2.design.patterns.abstractfactory;

/**
 * <p>Title: Square</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-abstract-factory-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-07 20:36
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-07   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
