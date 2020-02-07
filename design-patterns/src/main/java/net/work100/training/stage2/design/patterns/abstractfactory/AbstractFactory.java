package net.work100.training.stage2.design.patterns.abstractfactory;

/**
 * <p>Title: AbstractFactory</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-abstract-factory-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-07 20:56
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-07   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class AbstractFactory {
    /**
     * 获得 Color 对象
     *
     * @param colorType 颜色名称
     * @return
     */
    public abstract Color getColor(String colorType);

    /**
     * 获取 Shape 对象
     *
     * @param shapeType 形状名称
     * @return
     */
    public abstract Shape getShape(String shapeType);
}
