package com.mgkim.testDemo.net;

public interface IResultReceiver {
    void onResult(IRequest req, boolean isSuccess);
}
