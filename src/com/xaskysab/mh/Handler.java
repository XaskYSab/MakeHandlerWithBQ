package com.xaskysab.mh;

import java.util.concurrent.LinkedBlockingDeque;


public class Handler {

    LinkedBlockingDeque<Message> msgQueue;
    Looper myLooper;

    public Handler(){
        myLooper = Looper.myLooper();
        msgQueue = myLooper.msgQueue;
    }


    public void handleMessage(Message msg) {


    }

    public void sendMessage(Message msg){
        try {
            msg.target = this;
            msgQueue.put(msg);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void dispatchMessage(Message msg) {
        handleMessage(msg);
    }
}
