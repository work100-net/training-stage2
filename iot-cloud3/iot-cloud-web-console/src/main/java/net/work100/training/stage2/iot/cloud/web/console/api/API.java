package net.work100.training.stage2.iot.cloud.web.console.api;

/**
 * <p>Title: API</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-25 13:03
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-25   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class API {

    public static final String HOST = "http://localhost:9090";
    public static final String API_PATH = HOST + "/api/v1";

    public static final String API_AUTH_TENANT = API_PATH + "/auth/tenant/";
    public static final String API_AUTH_TENANT_GET = API_AUTH_TENANT;
}
