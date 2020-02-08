package net.work100.training.stage2.design.patterns.strategy;

/**
 * <p>Title: Strategy</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-strategy-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 17:03
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public interface Strategy {
    int doOperation(int num1, int num2);
}
