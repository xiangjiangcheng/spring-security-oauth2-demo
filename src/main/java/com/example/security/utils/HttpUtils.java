package com.example.security.utils;

import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Xiang Jiangcheng
 */
public class HttpUtils {

    private static final OkHttpClient HTTP_CLIENT = new OkHttpClient.Builder()
            .connectTimeout(5, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS).build();

    public static final MediaType APPLICATION_JSON = MediaType.parse("application/json");

    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    static {

    }

    public static String get(String url, HeaderBuilder headerBuilder) {
        Request.Builder builder = new Request.Builder().url(url);

        if (headerBuilder != null) {
            headerBuilder.build(builder);

        }

        Request request = builder.get().build();
        Response response = null;
        try {
            response = HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body == null) {
                    logger.error("[GET] {} response body is null", url);
                    return null;
                }
                return body.string();
            }
        } catch (Exception e) {
            logger.error("Exception happened when execute http GET", e);
        } finally {
            if (response != null) {
                ResponseBody body = response.body();
                if (body != null) {
                    body.close();
                }
            }
        }
        return null;
    }

    public static String get(String url, Map<String, String> headers) {

        Request.Builder builder = new Request.Builder().url(url);

        for (Map.Entry<String, String> headerEntry : headers.entrySet()) {
            builder.addHeader(headerEntry.getKey(), headerEntry.getValue());
        }
        Request request = builder.get().build();
        Response response = null;
        try {
            response = HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body == null) {
                    logger.error("[GET] {} response body is null", url);
                    return null;
                }
                return body.string();
            }
        } catch (Exception e) {
            logger.error("Exception happened when execute http GET", e);
        } finally {
            if (response != null) {
                ResponseBody body = response.body();
                if (body != null) {
                    body.close();
                }
            }
        }
        return null;
    }

    public static String post(String url, String content, MediaType contentType, HeaderBuilder headerBuilder) {
        Request.Builder builder = new Request.Builder().url(url);

        if (headerBuilder != null) {
            headerBuilder.build(builder);
        }

        Request request = builder.post(RequestBody.create(content, contentType)).build();
        Response response = null;
        try {
            response = HTTP_CLIENT.newCall(request).execute();
            if (response.isSuccessful()) {
                ResponseBody body = response.body();
                if (body == null) {
                    logger.error("[POST] {} response body is null", url);
                    return null;
                }
                return body.string();
            }

            int httpCode = response.code();
            logger.warn("POST[{}] Http request failed", httpCode);
            return null;
        } catch (IOException e) {
            logger.error("Exception happened when execute http POST", e);
        } finally {
            if (response != null) {
                ResponseBody body = response.body();
                if (body != null) {
                    body.close();
                }
            }
        }
        return null;
    }

    public interface HeaderBuilder {

        void build(Request.Builder builder);

    }

}