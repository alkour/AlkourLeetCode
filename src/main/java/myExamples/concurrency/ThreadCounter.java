package myExamples.concurrency;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadCounter extends Thread {
    private static int threadcounter = 0;
//    private static AtomicInteger atomicCounter = new AtomicInteger(0);
    public synchronized void run() {
        threadcounter++;
        System.out.println(threadcounter);
//        atomicCounter.incrementAndGet();
//        System.out.println(atomicCounter);
    }
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new ThreadCounter().start();
        }
        System.out.println("threadcounter value = " + threadcounter);

//        System.out.println("threadcounter value = " + atomicCounter);
    }
}
