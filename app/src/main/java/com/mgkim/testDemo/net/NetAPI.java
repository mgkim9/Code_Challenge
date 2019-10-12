package com.mgkim.testDemo.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public class NetAPI implements INetAPI{

    private HttpClient client;
    private ThreadManager imageManager;
    private ThreadManager localManager;

    private static class NetAPIHolder {
        private static INetAPI instance = new NetAPI();
    }

    public static INetAPI getInstance() {
        return NetAPIHolder.instance;
    }

    public NetAPI() {
        client = new DefaultHttpClient();
        imageManager = new ThreadManager(3, false);
        localManager = new ThreadManager(1, true);
    }

    @Override
    public void addReq(IRequest req) {
        if(req instanceof RequestImage) {
            imageManager.addReq(req);
        } else if (req instanceof RequestLocalWork) {
            localManager.addReq(req);
        }
    }

    @Override
    public HttpResponse execute(HttpGet get) {
        try {
            return client.execute(get);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
