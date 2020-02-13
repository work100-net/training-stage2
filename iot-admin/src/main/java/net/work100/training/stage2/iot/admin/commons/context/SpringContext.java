package net.work100.training.stage2.iot.admin.commons.context;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * <p>Title: SpringContext</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-example.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-13 14:31
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-13   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public final class SpringContext {

    public Object getBean(String beanId) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        return context.getBean(beanId);
    }

}
