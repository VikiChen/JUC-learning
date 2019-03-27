package com.viki.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class TestCallable {
    public static void main(String[] args) {
        ThreadDemoCall td =new ThreadDemoCall();
        FutureTask<Integer> result =new FutureTask<>(td);
        new Thread(result).start();
        try {
            Integer sum = result.get();
            System.out.println(sum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}

class ThreadDemoCall implements Callable<Integer>{

    @Override
    public Integer call() {
        int sum =0;
        for (int i=0;i<=100;i++){
            sum+=i;
        }
        return sum;
    }
}

//class ThreadDemp implements Runnable{
//    @Override
//    public void run() {
//
//    }
//}