package com.wupaas.boot.core.util;

import lombok.extern.slf4j.Slf4j;

import javax.net.ssl.*;
import java.io.*;
import java.net.URL;
import java.security.cert.X509Certificate;

/**
 * 不校验https
 * 信任域名使用
 *
 * @author jueyue on 19-11-16.
 */
@Slf4j
public class HttpsUtil {
    /**
     * Get 请求
     *
     * @param url    http请求地址
     * @param params http请求参数
     * @return String
     */
    public static String get(String url, String params) {
        BufferedReader br            = null;
        String         urlNameString = null;
        try {
            urlNameString = url;
            if (0 != params.length()) {
                String linkOperator = urlNameString.contains("?") ? "&" : "?";
                urlNameString += linkOperator + params;
            }
            HttpsURLConnection connection = trustAll(urlNameString);
            return readString(connection.getInputStream());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static HttpsURLConnection trustAll(String urlNameString) throws Exception {
        TrustManager[] trustAllCerts = new TrustManager[]{
                new X509TrustManager() {
                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                        // don't check
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                        // don't check
                    }
                }
        };
        SSLContext sslContext = SSLContext.getInstance("TLS");
        sslContext.init(null, trustAllCerts, null);
        SSLSocketFactory   ssf        = sslContext.getSocketFactory();
        HttpsURLConnection connection = (HttpsURLConnection) new URL(urlNameString).openConnection();
        connection.setSSLSocketFactory(ssf);

        // 设置请求头
        connection.setRequestProperty("accept", "*/*");
        connection.setRequestProperty("connection", "Keep-Alive");
        connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

        connection.connect();
        return connection;
    }

    /**
     * POST 请求
     *
     * @param url    http请求地址
     * @param params http请求参数
     * @return String
     */
    public static String post(String url, String params) {
        PrintWriter    pw = null;
        BufferedReader br = null;
        try {
            HttpsURLConnection connection = trustAll(url);
            connection.setRequestMethod("POST");
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);

            connection.setConnectTimeout(5000);
            pw = new PrintWriter(connection.getOutputStream());
            pw.print(params);
            pw.flush();
            return readString(connection.getInputStream());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return null;
    }


    private static String readString(InputStream is) {
        BufferedReader br      = null;
        String         content = "";
        try {
            br = new BufferedReader(new InputStreamReader(is, "utf-8"));
            String line;
            while ((line = br.readLine()) != null) {
                content += line;
            }
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            }
        }
        return content;
    }
}