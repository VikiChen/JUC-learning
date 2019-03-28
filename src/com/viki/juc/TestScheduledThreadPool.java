package com.viki.juc;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestScheduledThreadPool {

    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(6);
        pool.schedule(new Callable<Integer>() {
            @Override
            public Integer call() {
                int num =new Random().nextInt(100);
                System.out.println(Thread.currentThread().getName()+":"+num);
                return num;
            }
        },3,TimeUnit.SECONDS);
        pool.shutdown();
    }
}
