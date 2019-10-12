package com.mgkim.testDemo.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

public abstract class RequestGet extends Request {
    public abstract HttpGet getHttpGet();
    public abstract void onResult(HttpResponse res);

    @Override
    public void send() {
        try {
            HttpResponse res = NetAPI.getInstance().execute(getHttpGet());
            isSuccess = true;
            onResult(res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
