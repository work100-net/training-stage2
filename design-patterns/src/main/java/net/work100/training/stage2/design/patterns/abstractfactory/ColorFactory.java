package net.work100.training.stage2.design.patterns.abstractfactory;

/**
 * <p>Title: ColorFactory</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-abstract-factory-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-07 21:03
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-07   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String colorType) {
        if (colorType == null) {
            return null;
        }
        if (colorType.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (colorType.equalsIgnoreCase("GREEN")) {
            return new Green();
        } else if (colorType.equalsIgnoreCase("BLUE")) {
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shapeType) {
        return null;
    }
}
