package net.work100.training.stage2.iot.cloud.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Title: HttpClientUtils</p>
 * <p>Description: </p>
 *
 * @author liuxiaojun
 * @date 2020-03-24 16:25
 * ------------------- History -------------------
 * <date>      <author>       <desc>
 * 2020-03-24   liuxiaojun     初始创建
 * -----------------------------------------------
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";

    public static final String REQUEST_HEADER_CONNECTION = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";


    /**
     * GET 请求
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {
        return createHttpClient(url, GET, null, null);
    }

    /**
     * GET 请求
     *
     * @param url    请求地址
     * @param method 请求方法
     * @param cookie 请求Cookie
     * @return
     */
    public static String doGet(String url, String method, String cookie) {
        return createHttpClient(url, method, cookie, null);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, List<BasicNameValuePair> params) {
        return createHttpClient(url, POST, null, params);
    }

    /**
     * POST 请求
     *
     * @param url    请求地址
     * @param cookie 请求Cookie
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, String cookie, List<BasicNameValuePair> params) {
        return createHttpClient(url, POST, cookie, params);
    }

    /**
     * 创建请求
     *
     * @param url    请求地址
     * @param method 请求方法
     * @param cookie 请求Cookie
     * @param params 请求参数（仅限POST）
     * @return
     */
    private static String createHttpClient(String url, String method, String cookie, List<BasicNameValuePair> params) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;

        try {

            HttpGet httpGet = null;
            HttpPost httpPost = null;
            CloseableHttpResponse httpResponse = null;


            // GET 请求
            if (GET.equalsIgnoreCase(method)) {
                httpGet = new HttpGet(url);
                httpGet.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpGet.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                httpGet.setHeader("Cookie", cookie);
                httpResponse = httpClient.execute(httpGet);
            }
            // POST 请求
            else {
                httpPost = new HttpPost(url);
                httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION);
                httpPost.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT);
                httpPost.setHeader("Cookie", cookie);

                // 参数
                if (params != null && params.size() > 0) {
                    httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                }

                try {
                    httpResponse = httpClient.execute(httpPost);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            HttpEntity httpEntity = httpResponse.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (IOException ex) {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
