package com.mgkim.testDemo.net;

public abstract class Request implements IRequest {
    protected IResultReceiver resultReceiver;
    private boolean isCancel = false;
    protected boolean isSuccess = false;
    protected int needRetry = 0;

    @Override
    public final void cancel() {
        isCancel = true;
    }

    @Override
    public void failed() {
        notifyResult(false);
    }

    @Override
    public final boolean isCancel() {
        return isCancel;
    }

    @Override
    public final boolean isSuccess() {
        return isSuccess;
    }

    @Override
    public boolean needRetry() {
        return needRetry-- > 0;
    }

    public void setIResultReceiver(IResultReceiver resultReceiver) {
        this.resultReceiver = resultReceiver;
    }

    protected final void notifyResult(boolean isSuccess) {
        if(resultReceiver != null) {
            resultReceiver.onResult(this, isSuccess);
        }
    }
    protected void released() {
        resultReceiver = null;
    }
}
