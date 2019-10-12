package com.mgkim.testDemo.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;

public interface INetAPI {
    void addReq(IRequest req);
    HttpResponse execute(HttpGet get);
}
