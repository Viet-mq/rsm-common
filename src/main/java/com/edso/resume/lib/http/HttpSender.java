package com.edso.resume.lib.http;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class HttpSender {

    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final OkHttpClient client = new OkHttpClient.Builder().connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(300, TimeUnit.SECONDS).build();

    public abstract JsonObject postJson(String uri, Map<String, String> header, JsonObject params);

    public abstract JsonObject postJson(String uri, Map<String, String> header, JsonElement params);

    public abstract JsonObject postForm(String uri, Map<String, String> header, Map<String, String> params);

    public abstract JsonObject get(String uri, Map<String, String> header, Map<String, String> params);

    public abstract JsonObject get(String uri, Map<String, String> header, Map<String, String> params, boolean print);

    JsonObject postForm(String uri, Map<String, String> header, Map<String, String> params, int timeout) {
        JsonObject result = null;
        try {
            logger.info("POST FORM uri={}, params={}", uri, new Gson().toJson(params));
            FormBody.Builder formBodyBuilder = new FormBody.Builder();
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    formBodyBuilder.addEncoded(entry.getKey(), entry.getValue());
                }
            }
            Request.Builder requestBuilder = new Request.Builder().url(uri).post(formBodyBuilder.build())
                    .addHeader("Content-Type", "application/x-www-form-urlencoded");
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            Request request = requestBuilder.build();
            String rs = decodeResponse(client, request);
            logger.info("POST FORM result: {}", rs);
            if (!Strings.isNullOrEmpty(rs)) {
                JsonParser parser = new JsonParser();
                result = parser.parse(rs).getAsJsonObject();
            }
            return result;
        } catch (Exception ex) {
            logger.error("", ex);
            return null;
        }
    }

    JsonObject postJson(String uri, Map<String, String> header, String params, int timeout) {
        JsonObject result = null;
        try {
            logger.info("POST JSON uri={}, params={}", uri, params);
            RequestBody body = RequestBody
                    .create(JSON, params);
            Request.Builder requestBuilder = new Request.Builder().url(uri).post(body)
                    .addHeader("Content-Type", "application/json; charset=utf-8");
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            Request req = requestBuilder.build();
            String rs = decodeResponse(client, req);
            logger.info("POST JSON result: {}", rs);
            if (!Strings.isNullOrEmpty(rs)) {
                JsonParser parser = new JsonParser();
                result = parser.parse(rs).getAsJsonObject();
            }
            return result;
        } catch (Exception ex) {
            logger.error("POST Exception: ", ex);
            return null;
        }
    }

    protected JsonObject getJson(String uri, Map<String, String> header, Map<String, String> params, int timeout) {
        JsonObject result = null;
        try {
            String resp = get(uri, header, params, timeout);
            if (!Strings.isNullOrEmpty(resp)) {
                JsonParser parser = new JsonParser();
                result = parser.parse(resp).getAsJsonObject();
            }
            return result;
        } catch (Exception ex) {
            logger.error("", ex);
            ex.printStackTrace();
            return null;
        }
    }

    protected String get(String uri, Map<String, String> header, Map<String, String> params, int timeout) {
        try {
            HttpUrl.Builder urlBuilder = HttpUrl.parse(uri).newBuilder();
            if (params != null && !params.isEmpty()) {
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    urlBuilder.addQueryParameter(entry.getKey(), entry.getValue());
                }
            }
            String url = urlBuilder.build().toString();
            Request.Builder requestBuilder = new Request.Builder().url(url).get();
            if (header != null && !header.isEmpty()) {
                for (Map.Entry<String, String> entry : header.entrySet()) {
                    requestBuilder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            Request request = requestBuilder.build();
            return decodeResponse(client, request);
        } catch (Exception ex) {
            logger.error("", ex);
            return "";
        }
    }

    private String decodeResponse(OkHttpClient client, Request request) throws IOException {
        ResponseBody body = null;
        try {
            Response response = client.newCall(request).execute();
            if (response.code() != 200 && response.code() != 201) {
                return "";
            }
            body = response.body();
            if (body == null) {
                return "";
            }
            return body.string();
        } catch (final Throwable th) {
            logger.error("", th);
            return "";
        } finally {
            if (body != null) {
                try {
                    body.close();
                } catch (final Throwable th) {
                    logger.error("", th);
                }
            }
        }
    }

}