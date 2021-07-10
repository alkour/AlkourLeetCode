package myExamples.multithreading;

public class MyThread extends Thread{

    public void run() {
        System.out.println("The tread is running. Current thread name is = " +
                Thread.currentThread().getName());
        try {
            this.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        System.out.println("The tread is running. Current thread name is = " +
                Thread.currentThread().getName());
        MyThread myThread = new MyThread();
        myThread.start();

    }
}
