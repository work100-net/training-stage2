package net.work100.training.stage2.hello.spring.service.impl;

import net.work100.training.stage2.hello.spring.service.UserService;

/**
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-02-12 13:32
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-12   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class UserServiceImpl implements UserService {
    public void sayHi() {
        System.out.println("Hello Spring");
    }
}
