package net.work100.training.stage2.iot.cloud.commons.utils;

import net.work100.training.stage2.iot.cloud.commons.dto.AbstractBaseDomain;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>Title: SessionUtils</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-27 16:26
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-27   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class SessionUtils {

    /**
     * 获取Session对象
     *
     * @param request
     * @param key
     * @param <T>
     * @return
     */
    public static <T extends AbstractBaseDomain> T get(HttpServletRequest request, String key) {
        return (T) request.getSession().getAttribute(key);
    }

    /**
     * 获取Session字符串
     *
     * @param request
     * @param key
     * @return
     */
    public static String getString(HttpServletRequest request, String key) {
        return request.getSession().getAttribute(key).toString();
    }

    /**
     * 设置Session对象
     *
     * @param request
     * @param key
     * @param object
     * @param <T>
     */
    public static <T extends AbstractBaseDomain> void set(HttpServletRequest request, String key, T object) {
        request.getSession().setAttribute(key, object);
    }

    /**
     * 设置Session字符串
     *
     * @param request
     * @param key
     * @param value
     */
    public static void set(HttpServletRequest request, String key, String value) {
        request.getSession().setAttribute(key, value);
    }

    /**
     * 清空Session
     *
     * @param request
     */
    public static void clear(HttpServletRequest request) {
        request.getSession().invalidate();
    }
}
