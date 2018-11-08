package com.lemeng.lecloud.utils.http;

import java.security.cert.CertificateException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 使用http连接池发送请求
 * 
 * @author WL-PC
 *
 */
public class CustHttpClientManager {

	private final static int MAX_TOTAL = 30;// 最大连接数
	private final static int DEFAULT_MAX_PER_ROUTE = 5;// 最大初始数

	private final static Logger LOGGER = LoggerFactory.getLogger(CustHttpClientManager.class);
	private static PoolingHttpClientConnectionManager cm = null;

	static {
		// 绕过ssl认证
		try {
			SSLContext sc = SSLContext.getInstance("SSLv3");
			// 实现一个X509TrustManager接口，用于绕过验证，不用修改里面的方法
			X509TrustManager trustManager = new X509TrustManager() {
				@Override
				public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
				}

				@Override
				public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate, String paramString)
						throws CertificateException {
				}

				@Override
				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			};
			sc.init(null, new TrustManager[] { trustManager }, null);
			// 设置协议http和https对应的处理socket链接工厂的对象
			SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sc, new String[] { "TLSv1.2" }, null, new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			});
			// 支持 http\https
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
					.register("http", PlainConnectionSocketFactory.INSTANCE).register("https", sf).build();
			cm = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			cm.setMaxTotal(MAX_TOTAL);
			cm.setDefaultMaxPerRoute(DEFAULT_MAX_PER_ROUTE);
			LOGGER.info("CustHttpClientManager SSL初始化,MAX_TOTAL：" + MAX_TOTAL + ",DEFAULT_MAX_PER_ROUTE:" + DEFAULT_MAX_PER_ROUTE);
		} catch (Exception e) {
			LOGGER.info(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	public static CloseableHttpClient getHttpClient() {
		CloseableHttpClient client = HttpClients.custom().setConnectionManager(cm).build();
		return client;
	}

}
