package net.work100.training.stage2.iot.cloud.web.console.api;

/**
 * <p>Title: API</p>
 * <p>Description: </p>
 * <p>Url: http://www.work100.net/training/monolithic-project-iot-cloud-console.html</p>
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

    public static final String API_AUTH_TENANT = API_PATH + "/auth/tenant";
    public static final String API_AUTH_TENANT_GET = API_AUTH_TENANT + "/%s";
    public static final String API_AUTH_TENANT_SAVE = API_AUTH_TENANT + "/%s/save";

    public static final String API_AUTH_TENANT_LOGIN = API_PATH + "/auth/tenant/%s/login";

    public static final String API_AUTH_TENANT_USER = API_PATH + "/auth/tenant/%s/user";
    public static final String API_AUTH_TENANT_USER_PAGE_SEARCH = API_AUTH_TENANT_USER + "/page-search";
}
