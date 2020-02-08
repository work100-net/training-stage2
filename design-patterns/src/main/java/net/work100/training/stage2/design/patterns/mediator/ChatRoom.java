package net.work100.training.stage2.design.patterns.mediator;

import java.util.Date;

/**
 * <p>Title: ChatRoom</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-architecture-design-patterns-mediator-pattern.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-08 16:26
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-08   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class ChatRoom {
    public static void showMessage(User user, String message) {
        System.out.println(new Date().toString() + " [" + user.getName() + "] : " + message);
    }
}
