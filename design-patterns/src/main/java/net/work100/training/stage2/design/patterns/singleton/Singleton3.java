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
public class Singleton3 {
    private static Singleton3 instance = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance() {
        return instance;
    }
}
