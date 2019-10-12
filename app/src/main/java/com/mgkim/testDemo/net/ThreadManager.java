package com.mgkim.testDemo.net;

class ThreadManager implements IRequestQueueConsumer{
    private int maxSize;
    private int curSize = 0;
    private RequestQueue queue;

    public ThreadManager(int maxSize, boolean isFIFO) {
        this.maxSize = maxSize;
        queue = new RequestQueue(isFIFO);
    }

    @Override
    public IRequest take() {
        return queue.take();
    }

    @Override
    public void destroyed() {
        curSize--;
    }

    public void addReq(IRequest req) {
        synchronized(this) {
            queue.addReq(req);
            if(curSize < maxSize) {
                new WorkThread(this).start();
                curSize++;
            }
        }
    }
}
