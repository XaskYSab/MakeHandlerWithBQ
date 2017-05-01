package com.xaskysab.mh;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;


public class Main {

    public static void main(String[]args){

        Looper.prepare();

        Handler handler =new Handler() {
            @Override
            public void handleMessage(Message msg) {

                System.out.println("get--->"+msg.toString());

            }
        };

        ExecutorService es = Executors.newFixedThreadPool(3);

        Stream.of(new String[3]).forEach(x->{
            es.execute(()->{
                Stream.generate(()-> Math.random()*1000).forEach(y->{
                    Message msg = new Message();
                    msg.obj = Thread.currentThread().getName()+":"+y;
                    System.out.println("send--->"+ msg.toString());
                    handler.sendMessage(msg);
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            });
        });


        System.out.println("----1");

        Looper.loop();

        System.out.println("----2");

    }
}
