package net.work100.training.stage2.design.patterns.state;

/**
 * <p>Title: State</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-state-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:51
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface State {
    /**
     * 执行Action
     *
     * @param context
     */
    void doAction(Context context);
}
