package com.lemeng.lecloud.utils.http;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpHelper {

    private final static Logger LOGGER = LoggerFactory.getLogger(HttpHelper.class);

    private final static int SOCKET_TIMEOUT = 25000;// 请求超时时间
    private final static int CONNECT_TIMEOUT = 30000;// 传输超时时间

    private static RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(SOCKET_TIMEOUT)
            .setConnectTimeout(CONNECT_TIMEOUT).build();

    /**
     * 处理http请求的post方法
     *
     * @param url
     * @param requestMap
     * @return
     * @throws Exception
     */
    public static String doPost(String url, Map<String, String> requestMap) throws Exception {
        try {
            long n = System.nanoTime();
            LOGGER.info("请求id:" + n + "请求发送url:" + url + ",请求参数：" + requestMap);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            CloseableHttpResponse response = null;
            List<BasicNameValuePair> formparams = getFormparams(requestMap);
            httpPost.setEntity(new UrlEncodedFormEntity(formparams, "utf-8"));
            response = CustHttpClientManager.getHttpClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity, "UTF-8");
            LOGGER.info("请求id:" + n + "返回结果为：" + str);
            return str;
        } catch (Exception e) {
            LOGGER.error("postSSL发生错误：" + e.getMessage(), e);
            throw new Exception("post请求发生错误：" + e.getMessage(), e);
        }
    }

    /**
     * PostJson请求
     *
     * @param url
     * @param requestMap
     * @return
     * @throws Exception
     */
    public static String doPostByJson(String url, Map<String, String> requestMap) throws Exception {
        try {
            long n = System.nanoTime();
            LOGGER.info("请求id:" + n + "请求发送url:" + url + ",请求参数：" + requestMap);
            List<BasicNameValuePair> formparams = getFormparams(requestMap);
            CloseableHttpResponse response = null;
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams);
            HttpPost httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json");
            uefEntity.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            httpPost.setEntity(uefEntity);
            httpPost.setConfig(requestConfig);// 设置请求参数
            response = CustHttpClientManager.getHttpClient().execute(httpPost);
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity, "UTF-8");
            LOGGER.info("请求id:" + n + "返回结果为：" + str);
            return str;
        } catch (Exception e) {
            LOGGER.error("doPostByJson发生错误：" + e.getMessage(), e);
            throw new Exception("doPostByJson发生错误：" + e.getMessage(), e);
        }
    }

    /**
     * /** getUrl请求
     *
     * @param url
     * @param requestMap
     * @return
     * @throws Exception
     */
    public static String doGetByUrl(String url) throws Exception {
        try {
            long n = System.nanoTime();
            LOGGER.info("请求发送url:" + url + "请求id:" + n);
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(requestConfig);// 设置请求和传输超时时间
            CloseableHttpResponse response = CustHttpClientManager.getHttpClient().execute(httpGet);
            HttpEntity entity = response.getEntity();
            String str = EntityUtils.toString(entity, "UTF-8");
            LOGGER.info("请求id:" + n + "返回结果为：" + str);
            return str;
        } catch (Exception e) {
            LOGGER.error("doGetByUrl发生错误：" + e.getMessage(), e);
            throw new Exception("doGetByUrl发生错误：" + e.getMessage(), e);
        }
    }

    /**
     * 将map转换为表单列表数据
     *
     * @param paramsMap
     * @return
     */
    private static List<BasicNameValuePair> getFormparams(Map<String, String> requestMap) {
        List<BasicNameValuePair> formparams = new ArrayList<BasicNameValuePair>();
        if (requestMap != null && !requestMap.isEmpty()) {
            Iterator<String> it = requestMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                String value = requestMap.get(key);
                formparams.add(new BasicNameValuePair(key, value));
            }
        }
        return formparams;
    }

    /**
     * 将http返回json结果转换为map
     *
     * @param responseStr
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> setResponseStrToMap(String responseStr) throws Exception {
        try {
            Map<String, Object> resultMap = null;
            ObjectMapper mapper = new ObjectMapper();
            resultMap = mapper.readValue(responseStr, HashMap.class);
            return resultMap;
        } catch (Exception e) {
            LOGGER.error("返回结果json转map发生错误", e);
            throw new Exception("返回结果json转map发生错误:" + e.getMessage(), e);
        }
    }

    public static void main(String[] args) throws Exception {
        String url = "http://localhost:7788/getJson/url.kh.weixin.push/?link_url=http%3A%2F%2Fm.ctsec.com%2Fkh.html&account=13510009482&template_key=OPENTM207514315&weixin_pk=gh_a6b2a0632f21&account_type=2&send_data=%7B%27first%27%3A%7B%27color%27%3A%27%23173177%27%2C%27value%27%3A%27%E5%BC%80%E6%88%B7%E5%AE%A1%E6%A0%B8%E5%A4%B1%E8%B4%A5%E6%8F%90%E9%86%92%27%7D%2C%27keyword1%27%3A%7B%27color%27%3A%27%23173177%27%2C%27value%27%3A%27%E8%AF%81%E5%88%B8%E5%BC%80%E6%88%B7%27%7D%2C%27keyword2%27%3A%7B%27color%27%3A%27%23173177%27%2C%27value%27%3A%27%24date%24%27%7D%2C%27keyword3%27%3A%7B%27color%27%3A%27%23173177%27%2C%27value%27%3A%27%E6%82%A8%E6%8F%90%E4%BA%A4%E7%9A%84%E5%BC%80%E6%88%B7%E4%BF%A1%E6%81%AF%E9%83%A8%E5%88%86%E6%9C%89%E8%AF%AF%27%7D%2C%27keyword4%27%3A%7B%27color%27%3A%27%23173177%27%2C%27value%27%3A%27%E8%AF%B7%E7%99%BB%E5%BD%95%E8%B4%A2%E9%80%9A%E8%AF%81%E5%88%B8APP-%E5%BC%80%E6%88%B7%E9%A6%96%E9%A1%B5%EF%BC%8C%E7%BB%A7%E7%BB%AD%E5%BC%80%E6%88%B7%E4%BF%AE%E6%94%B9%E7%9B%B8%E5%85%B3%E4%BF%A1%E6%81%AF%E5%90%8E%E9%87%8D%E6%96%B0%E6%8F%90%E4%BA%A4%E3%80%82%27%7D%2C%27remark%27%3A%7B%27color%27%3A%27%23173177%27%2C%27value%27%3A%27%E7%82%B9%E5%87%BB%E4%B8%8B%E8%BD%BDAPP%E4%BF%AE%E6%94%B9%3E%3E%27%7D%7D";
        String res = HttpHelper.doGetByUrl(url);
        System.out.println(res);
        System.out.println(setResponseStrToMap(res));
    }
}
