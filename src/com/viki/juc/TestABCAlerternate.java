package com.viki.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TestABCAlerternate {

    public static void main(String[] args) {
        PringThread pd =new PringThread();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    pd.printA();
                }

            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (true){
                   pd.printB();
               }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
               while (true){
                   pd.printC();
               }
            }
        }).start();
    }

}

class PringThread{
    Lock lock =new ReentrantLock();
    Condition conditionA=lock.newCondition();
    Condition conditionB=lock.newCondition();
    Condition conditionC=lock.newCondition();
    String flag ="A";

    public void printA(){
        lock.lock();
        if (flag=="A"){
              System.out.println("A");
              flag="B";
              conditionB.signal();
        }else {
            try {
                conditionA.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        lock.unlock();
    }

    public void printB(){
        lock.lock();
        if (flag=="B"){
            System.out.println("B");
            flag="C";
            conditionC.signalAll();
        }else {
            try {
                conditionB.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        lock.unlock();
    }

    public void printC(){
        lock.lock();
        if (flag=="C"){
            System.out.println("C");
            flag="A";
            conditionA.signalAll();
        }else {
            try {
                conditionC.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        lock.unlock();
    }

}
