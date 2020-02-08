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
public class Singleton2 {
    private static Singleton2 instance;

    private Singleton2() {

    }

    /**
     * synchronized 确保线程安全
     *
     * @return
     */
    public static synchronized Singleton2 getInstance() {
        if (instance == null) {
            return new Singleton2();
        }
        return instance;
    }
}
