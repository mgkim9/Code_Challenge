package com.mgkim.testDemo.net;

public class RequestIPC extends RequestLocalWork {

    public RequestIPC(int type) {
        super(type);
    }

    @Override
    protected Object doInBackground() {
        // TODO DB work case in type
        return null;
    }
}
