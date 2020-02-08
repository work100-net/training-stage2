package net.work100.training.stage2.design.patterns.facade;

/**
 * <p>Title: ShapeMaker</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-facade-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 15:12
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    /**
     * 画圆
     */
    public void drawCircle() {
        circle.draw();
    }

    /**
     * 画长方形
     */
    public void drawRectangle() {
        rectangle.draw();
    }

    /**
     * 画正方形
     */
    public void drawSquare() {
        square.draw();
    }
}
