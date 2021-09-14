package com.edso.resume.lib.http;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.Map;

public class HttpSenderImpl extends HttpSender {

    @Override
    public JsonObject postJson(String uri, Map<String, String> header, JsonObject params) {
//        int timeout = AppConfig.getConfig().getIntProperty("RECHARGE_TIMEOUT", 59000, "SETTINGS");
        int timeout = 59000;
        return postJson(uri, header, params.toString(), timeout);
    }

    @Override
    public JsonObject postJson(String uri, Map<String, String> header, JsonElement params) {
//        int timeout = AppConfig.getConfig().getIntProperty("RECHARGE_TIMEOUT", 59000, "SETTINGS");
        int timeout = 59000;
        return postJson(uri, header, params.toString(), timeout);
    }


    @Override
    public JsonObject postForm(String uri, Map<String, String> header, Map<String, String> params) {
//        int timeout = AppConfig.getConfig().getIntProperty("RECHARGE_TIMEOUT", 59000, "SETTINGS");
        int timeout = 59000;
        return postForm(uri, header, params, timeout);
    }

    public JsonObject get(String uri, Map<String, String> header, Map<String, String> params, boolean print) {
//        int timeout = AppConfig.getConfig().getIntProperty("RECHARGE_TIMEOUT", 59000, "SETTINGS");
        int timeout = 59000;
        return getJson(uri, header, params, timeout);
    }

    @Override
    public JsonObject get(String uri, Map<String, String> header, Map<String, String> params) {
        return get(uri, header, params, true);
    }

}
