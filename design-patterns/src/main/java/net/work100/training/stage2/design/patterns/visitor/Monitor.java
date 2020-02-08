package net.work100.training.stage2.design.patterns.visitor;

/**
 * <p>Title: Monitor</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-visitor-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 17:13
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
