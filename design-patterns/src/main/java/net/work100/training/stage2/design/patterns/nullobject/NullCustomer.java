package net.work100.training.stage2.design.patterns.nullobject;

/**
 * <p>Title: NullCustomer</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-null-object-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 17:00
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class NullCustomer extends AbstractCustomer {
    @Override
    public boolean isNil() {
        return true;
    }

    @Override
    public String getName() {
        return "Not Available in Customer Database";
    }
}
