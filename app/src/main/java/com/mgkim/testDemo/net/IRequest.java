package com.mgkim.testDemo.net;

public interface IRequest {
    void send();
    void cancel();
    void failed();
    boolean isCancel();
    boolean isSuccess();
    boolean needRetry();
}
