package com.mgkim.testDemo.net;

import java.util.Deque;
import java.util.LinkedList;

class RequestQueue {
    private boolean isFIFO = false;
    private Deque<IRequest> queue;

    public RequestQueue(boolean isFIFO) {
        this.isFIFO = isFIFO;
        queue = new LinkedList<>();
    }

    public void addReq(IRequest req) {
        synchronized (this) {
            queue.add(req);
        }
    }

    public IRequest take() {
        synchronized (this) {
            if(queue.isEmpty()) {
                return null;
            }
            if(isFIFO) {    //First in First out
                return queue.poll();
            } else {    //Last in First out
                return queue.pop();
            }
        }
    }
}
