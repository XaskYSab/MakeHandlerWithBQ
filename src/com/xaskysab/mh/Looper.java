package com.xaskysab.mh;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.stream.Stream;


public class Looper {

    LinkedBlockingDeque<Message>msgQueue = new LinkedBlockingDeque<>();

    static ThreadLocal <Looper>threadLocal = new ThreadLocal<>();

    public static Looper myLooper() {
        return threadLocal.get();
    }

    public static void prepare() {

        if(threadLocal.get()!=null){
            throw new RuntimeException("the looper had prepared!");
        }

        threadLocal.set(new Looper());
    }

    public static void loop() {

        Looper looper = myLooper();

        if(looper==null){
            throw new RuntimeException("please call Looper.prepare() firstly");
        }


        Stream.generate(()->null).forEach((x)->{

            try {
                Message msg = looper.msgQueue.take();
                msg.target.dispatchMessage(msg);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        });




    }


}
