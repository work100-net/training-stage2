package net.work100.training.stage2.design.patterns.command;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: Broker</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-command-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 15:59
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Broker {
    private List<Order> orderList = new ArrayList<Order>();

    public void takeOrder(Order order) {
        orderList.add(order);
    }

    public void placeOrders() {
        for (Order order : orderList) {
            order.execute();
        }
        orderList.clear();
    }
}
