package net.work100.training.stage2.iot.admin.commons.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * <p>Title: SpringContext</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-frameworks-spring-web.html</p>
 *
 * @author liuxiaojun
 * @date 2020-02-13 14:31
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-13   liuxiaojun     初始创建
 * -----------------------------------------------
 */
@Component
public class SpringContext implements ApplicationContextAware, DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(SpringContext.class);

    private static ApplicationContext applicationContext;

    /**
     * 使用 ApplicationContext，通过 beanId 获取实例
     *
     * @param beanId
     * @param <T>
     * @return
     */
    public static <T> T getBean(String beanId) {
        assertContextInjected();
        return (T) applicationContext.getBean(beanId);
    }

    /**
     * 使用 ApplicationContext，通过 class 获取实例
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        assertContextInjected();
        return applicationContext.getBean(clazz);
    }

    public void destroy() throws Exception {
        logger.debug("销毁 ApplicationContext");
        applicationContext = null;
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContext.applicationContext = applicationContext;
    }

    private static void assertContextInjected() {
        if (applicationContext == null) {
            throw new RuntimeException("还未在 spring-context.xml 中配置 SpringContext 对象");
        }
    }
}
