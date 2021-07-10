package myExamples.concurrency;

import static java.lang.String.*;

public class Wait {
    public static void main(String[] args) {
//        Runnable r = () -> {
//            for (int i = 0; i < 5; i++) {
//                System.out.println("Before sleep. Current thread = " + Thread.currentThread().getName());
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    System.out.println("Exception in sleep");
//                }
//            }
//        };
//        Thread t = new Thread(r);
//        t.start();

        MyThread t = new MyThread("My Thread");
        t.start();
        System.out.println("X, Thread = " + Thread.currentThread().getName());
        synchronized (t) {
            try {
                System.out.println("Before wait. Current thread = " + Thread.currentThread().getName());
                t.wait(10_000);
            } catch (InterruptedException e){
                System.out.println("Best Regards from Exception" + e);
            }
        }
        System.out.println("Y - Printed after 10 sec");
    }

}

class MyThread extends Thread {
    MyThread(String name) {
        this.setName(name);
    }
    @Override
    public void run() {
            for (int i = 0; i < 7; i++) {
                System.out.println(format("Before sleep %s. Current thread = %s", i, Thread.currentThread().getName()));
                try {
                    Thread.sleep(2000);
                    synchronized (this) {
                        notifyAll();
                    }
                } catch (InterruptedException e) {
                    System.out.println("Exception in sleep");
                }
            }
    }
}
