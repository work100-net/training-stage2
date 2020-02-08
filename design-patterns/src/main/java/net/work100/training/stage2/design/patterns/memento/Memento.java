package net.work100.training.stage2.design.patterns.memento;

/**
 * <p>Title: Memento</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-memento-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:29
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Memento {
    private String state;

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
