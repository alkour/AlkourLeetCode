package myExamples.multithreading;

import java.lang.Thread;
class NameRunnable implements Runnable {
    public void run() {
        for (int x = 0; x < 4 ; x++) {
            System.out.println ("Run by " + Thread.currentThread().getName() +
                    ", x is " + x);
            try {
                Thread.sleep(0);
            } catch (InterruptedException ex) { }
        }
    }
}
public class ManyName {
    public static void main(String[] args) {
        NameRunnable nr = new NameRunnable();  // Step 1 – create instance of runnable implementation
        Thread one = new Thread(nr); // Step 2 –create the thread with instance of runnable as argument
        Thread two = new Thread(nr);
        Thread three = new Thread(nr);
        one.setName("ONE");  two.setName("TWO");   three.setName("THREE");
        one.setPriority(5);
        System.out.println("two priority " + two.getPriority());
        System.out.println("one priority " + one.getPriority());
        System.out.println("tree priority " + three.getPriority());
        one.start(); // Start the thread
//        try {
//            one.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        two.start();
        try { two.join(); } catch (InterruptedException ex) {
            System.out.println("This is problem ");}
        three.start();
    }
}
