package net.work100.training.stage2.design.patterns.memento;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: CareTaker</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-memento-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:31
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class CareTaker {
    private List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state){
        mementoList.add(state);
    }

    public Memento get(int index){
        return mementoList.get(index);
    }
}
