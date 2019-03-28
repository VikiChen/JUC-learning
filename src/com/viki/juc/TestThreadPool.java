package com.viki.juc;

import java.util.concurrent.*;

public class TestThreadPool {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
//        ThreadPoolDemo threadPoolDemo =new ThreadPoolDemo();
////        new Thread(threadPoolDemo).start();
////        new Thread(threadPoolDemo).start();
        ExecutorService pool = Executors.newFixedThreadPool(5);
//        ThreadPoolDemo tpd =new ThreadPoolDemo();
//        Future<?> submit = pool.submit(tpd);
//        pool.shutdown();
        Future<Integer> future = pool.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                int sum = 0;
                for (int i = 0; i < 100; i++) {
                    sum += i;
                }
                return sum;
            }
        });

        System.out.println( future.get());
        pool.shutdown();
    }
}

class ThreadPoolDemo implements Runnable{

    private int i=0;

    @Override
    public void run() {
        while (i<=100){
            System.out.println(Thread.currentThread().getName()+":"+i++);
        }
    }
}
