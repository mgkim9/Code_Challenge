package com.mgkim.testDemo.net;

class WorkThread extends Thread{
    private IRequestQueueConsumer iRequestQueueConsumer;

    public WorkThread(IRequestQueueConsumer iRequestQueueConsumer) {
        this.iRequestQueueConsumer = iRequestQueueConsumer;
    }

    @Override
    public void run() {
        IRequest req = null;

        do {
            req = iRequestQueueConsumer.take();
            if(req == null) {
                break;
            }
            if(req.isCancel()) {
                continue;
            }
            req.send();
            if(!req.isSuccess() && !req.isCancel()) {
                while (!req.isSuccess() && !req.isCancel() && req.needRetry()) {
                    try {
                        Thread.sleep(10);
                        req.send();
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(!req.isSuccess() && !req.isCancel()) {
                    req.failed();
                }
            }
        } while (req != null);

        destroyed();
        super.run();
    }

    private void destroyed() {
        if(iRequestQueueConsumer != null) {
            iRequestQueueConsumer.destroyed();
        }
    }
}
