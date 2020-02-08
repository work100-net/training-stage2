package net.work100.training.stage2.design.patterns.singleton;

/**
 * <p>Title: Singleton</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-singleton-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 11:44
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Singleton1 {
    private static Singleton1 instance;

    private Singleton1() {
    }

    /**
     * 线程不安全
     *
     * @return
     */
    public static Singleton1 getInstance() {
        if (instance == null) {
            return new Singleton1();
        }
        return instance;
    }
}
