package com.viki.juc;

public class TestProductorAndConsumer {

    public static void main(String[] args) {

        Clerk clerk =new Clerk();

        Productor pro =new Productor(clerk);
        Consumer cus =new Consumer(clerk);
        new Thread(pro,"生产者A").start();
        new Thread(cus,"消费者B").start();
    }

}



class Clerk{

    private int product =0;


    public synchronized void get(){
        while (product>=1){
            System.out.println("产品已满无法添加");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName()+":"+ ++product);
            this.notifyAll();

    }

    public synchronized void sale(){
        while (product<=0){
            System.out.println("缺货");
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
            System.out.println(Thread.currentThread().getName()+":"+ --product);
            this.notifyAll();

    }

}


class Productor implements Runnable{
    private Clerk clerk;

    public Productor(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=1;i<20;i++){
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            clerk.get();
        }
    }
}


class Consumer implements Runnable{
    private Clerk clerk;

    public Consumer(Clerk clerk) {
        this.clerk = clerk;
    }

    @Override
    public void run() {
        for (int i=0;i<20;i++){
            clerk.sale();
        }
    }
}