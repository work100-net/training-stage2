package net.work100.training.stage2.design.patterns.prototype;

/**
 * <p>Title: Shape</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-prototype-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 12:38
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public abstract class Shape implements Cloneable {
    private String id;
    protected String type;

    /**
     * 绘制
     */
    abstract void draw();

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }
}
