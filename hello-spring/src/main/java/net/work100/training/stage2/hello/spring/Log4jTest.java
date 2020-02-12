package net.work100.training.stage2.hello.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <p>Title: Log4jTest</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-02-12 17:01
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-02-12   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class Log4jTest {
    public static final Logger logger = LoggerFactory.getLogger(Log4jTest.class);

    public static void main(String[] args) {
        logger.info("slf4j for info");
        logger.debug("slf4j for debug");
        logger.error("slf4j for error");
        logger.warn("slf4j for warn");

        String message = "Hello SLF4J";
        logger.info("slf4j message is : {}", message);
    }
}
