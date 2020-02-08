package net.work100.training.stage2.design.patterns.singleton;

/**
 * <p>Title: SingleObject</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-singleton-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 11:25
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class SingleObject {
    // 创建 SingleObject 的一个对象
    private static SingleObject instance = new SingleObject();

    // 让构造函数为 private，这样该类就不会被实例化
    private SingleObject() {

    }

    // 获取唯一可用的对象
    public static SingleObject getInstance() {
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello World!");
    }
}
