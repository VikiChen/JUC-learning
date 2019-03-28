package com.viki.juc;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TestReadWriteLock {
    public static void main(String[] args) {
        ReadWriteLcokDemo readWriteLcokDemo =new ReadWriteLcokDemo();
        new Thread(new Runnable() {
            @Override
            public void run() {
                readWriteLcokDemo.set((int)Math.random()*101);
            }
        },"write").start();
        for (int i=1;i<100;i++){
            new Thread(new Runnable() {
                @Override
                public void run() {
                   readWriteLcokDemo.get();
                }
            },"read").start();
        }
    }
}

class ReadWriteLcokDemo{
    private int number =0;
    private ReadWriteLock lock =new ReentrantReadWriteLock();

    public void get(){
        lock.readLock().lock();
        System.out.println(Thread.currentThread().getName()+":"+number);
        lock.readLock().unlock();
    }

    public void set(int number){
        lock.writeLock().lock();
        System.out.println(Thread.currentThread().getName());
        this.number=number;
        lock.writeLock().unlock();
    }
}


