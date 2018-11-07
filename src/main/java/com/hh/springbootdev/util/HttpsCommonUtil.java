/**
 * Copyright (c) 2008-2015 浩瀚深度 All Rights Reserved.
 *
 * <p>FileName: HttpsCommonUtil.java</p>
 *
 * @author jiangningning
 * @date 2018/11/7
 * @version 1.0
 * History:
 * v1.0.0, 姜宁宁 2018/11/7 Create
 */
package com.hh.springbootdev.util;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class HttpsCommonUtil {
    private static Logger logger = LoggerFactory.getLogger(HttpsCommonUtil.class);

    private static HostnameVerifier hv = (urlHostName, session) -> true;

    public static String httpsRequest(String url, String method, String params, Map<String, String> headers) {
        HttpsURLConnection conn = null;
        StringBuilder rsp = new StringBuilder();
        try {
            try {
                trustAllHttpsCertificates();
                HttpsURLConnection.setDefaultHostnameVerifier(hv);
                String urlStr = url;
                if (StringUtils.isNotEmpty(params)) {
                    urlStr += "?" + params;
                }
                conn = getConnection(new URL(urlStr), method, headers);
                conn.setConnectTimeout(60000);
                conn.setReadTimeout(60000);
            } catch (Exception e) {
                logger.error("GET_CONNECTOIN_ERROR, URL = " + url, e);
            }
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line;
                while ((line = in.readLine()) != null) {
                    rsp.append(line);
                }
            } catch (IOException e) {
                logger.error("REQUEST_RESPONSE_ERROR, URL = " + url, e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return rsp.toString();
    }

    private static HttpsURLConnection getConnection(URL url, String method,
                                                    Map<String, String> headers) throws IOException {
        HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
        conn.setRequestMethod(method);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Accept", "text/xml,text/javascript,text/html");
        conn.setRequestProperty("User-Agent", "stargate");
        Map<String, String> newHeaders = new HashMap<>();
        newHeaders.put("Content-Type", "application/x-www-form-urlencoded");
        if (headers != null) {
            newHeaders.putAll(headers);
        }
        for (Entry<String, String> entry : newHeaders.entrySet()) {
            conn.setRequestProperty(entry.getKey(), entry.getValue());
        }
        return conn;
    }

    private static void trustAllHttpsCertificates() throws Exception {
        // String key= PropertiesUtil.get("properties/keystore", "orderReceipt.key.path");
        // String pass=PropertiesUtil.get("properties/keystore", "orderReceipt.key.pass");
        String key = "E:\\keys\\iamcetc\\iamcetc.keystore";
        String pass = "123456";
        KeyStore keystore = KeyStore.getInstance("JKS");  //创建一个keystore来管理密钥库
        keystore.load(new FileInputStream(key), pass.toCharArray());
        //创建jkd密钥访问库
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(keystore);                 //验证数据，可以不传入key密码
        //创建TrustManagerFactory,管理授权证书
        SSLContext sslc = SSLContext.getInstance("SSLv3");
        // 构造SSL环境，指定SSL版本为3.0，也可以使用TLSv1，但是SSLv3更加常用。
        sslc.init(null, tmf.getTrustManagers(), null);
        //KeyManager[] 第一个参数是授权的密钥管理器，用来授权验证。第二个是被授权的证书管理器，
        //用来验证服务器端的证书。只验证服务器数据，第一个管理器可以为null
        //构造ssl环境
        HttpsURLConnection.setDefaultSSLSocketFactory(sslc.getSocketFactory());
    }

    public static void main(String[] args) {
        String url = "https://iam.cetc.com.cn:8443/ThunderIAM/services/company?wsdl";
        String s = httpsRequest(url, "GET", null, null);
        System.out.println(s);
    }
}
