package net.work100.training.stage2.hello.spring;

import net.work100.training.stage2.hello.spring.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: MyTest</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-02-12 13:35
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-12   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class MyTest {
    public static void main(String[] args) {
        // 获取 Spring 容器
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("spring-context.xml");

        // 从 Spring 容器中获取对象
        UserService userService = (UserService)applicationContext.getBean("userService");
        userService.sayHi();
    }
}
