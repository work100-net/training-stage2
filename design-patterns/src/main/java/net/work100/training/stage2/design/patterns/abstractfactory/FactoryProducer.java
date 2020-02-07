package net.work100.training.stage2.design.patterns.abstractfactory;

/**
 * <p>Title: FactoryProducer</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-abstract-factory-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-07 21:09
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-07   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}
