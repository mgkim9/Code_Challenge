package com.mgkim.testDemo.utils;

import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

public class FormatUtil {
    private static String TAG = "FormatUtil";

    public static boolean isNullorEmpty(String targetString) {
        if (targetString == null || targetString.trim().equals("") || targetString.trim().equals("null"))
            return true;

        return false;
    }

    public static boolean isNullorEmpty(String targetString, boolean enableNull) {
        if (enableNull) {
            if (targetString == null || targetString.trim().equals(""))
                return true;
        } else {
            if (targetString == null || targetString.trim().equals("") || targetString.trim().equals("null"))
                return true;
        }


        return false;
    }

    public static boolean isNullorEmpty(JSONObject jsonObject) {
        if (jsonObject == null || jsonObject.length() == 0)
            return true;

        return false;
    }

    public static boolean isNullorEmpty(JsonObject jsonObject) {
        if (jsonObject == null || jsonObject.size() == 0)
            return true;

        return false;
    }

    public static boolean isNullorEmpty(JSONArray jsonArray) {
        if (jsonArray == null || jsonArray.length() == 0)
            return true;

        return false;
    }

    public static String getFileName(final String url) {
        String[] pathStrs = url.split("/");
        if (pathStrs.length > 0) {
            return pathStrs[pathStrs.length - 1];
        }
        return null;
    }
}