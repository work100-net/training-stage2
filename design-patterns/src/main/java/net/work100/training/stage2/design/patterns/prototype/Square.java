package net.work100.training.stage2.design.patterns.prototype;

/**
 * <p>Title: Square</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-prototype-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 12:45
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Square extends Shape {

    public Square() {
        type = "Square";
    }

    @Override
    void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
