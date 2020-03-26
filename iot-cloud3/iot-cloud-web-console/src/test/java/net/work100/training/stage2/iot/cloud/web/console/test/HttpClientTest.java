package net.work100.training.stage2.iot.cloud.web.console.test;

import net.work100.training.stage2.iot.cloud.commons.utils.HttpClientUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>Title: HttpClientTest</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-22 16:58
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-22   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class HttpClientTest {
    public static void main(String[] args) {
        get();
        System.out.println("--------------------------------------");
        post();
    }

    private static void get() {
        String result = HttpClientUtils.doGet("http://localhost:9090/api/v1/auth/tenant/100001");
        System.out.println(result);
    }

    private static void post() {
        List<BasicNameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("tenantName", "租户名称1"));
        params.add(new BasicNameValuePair("tenantDesc", "test111"));
        String result = HttpClientUtils.doPost("http://localhost:9090/api/v1/auth/tenant/100001/save", params);
        System.out.println(result);
    }
}
