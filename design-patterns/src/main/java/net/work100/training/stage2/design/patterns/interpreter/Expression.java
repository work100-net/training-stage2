package net.work100.training.stage2.design.patterns.interpreter;

/**
 * <p>Title: Expression</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-interpreter-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:03
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface Expression {
    /**
     * 解释
     *
     * @param context
     * @return
     */
    boolean interpret(String context);
}
