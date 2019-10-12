package com.mgkim.testDemo.net;

interface IRequestQueueConsumer {
    IRequest take();
    void destroyed();
}
