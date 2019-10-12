package com.mgkim.testDemo.net;

public class RequestLocalWork extends Request {
    private int type;
    private Object result;

    public RequestLocalWork(int type) {
        this.type = type;
    }

    @Override
    public final void send() {
        try {
            Object res = doInBackground();
            this.result = res;
            if(res != null) {
                isSuccess = true;
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        notifyResult(isSuccess);
    }

    @Override
    public boolean needRetry() {
        return false;
    }

    protected Object doInBackground() {
        if(resultReceiver instanceof IDoInBackground) {
            return ((IDoInBackground) resultReceiver).doInBackground();
        }
        return null;
    }

    public void setIResultReceiver(IDoInBackground receiver) {
        super.setIResultReceiver(receiver);
    }

    public int getType() {
        return type;
    }

    public Object getResult() {
        return result;
    }
}
