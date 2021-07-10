package myExamples.concurrency;

public class WaitNotify {
    public Mumbo mumbo= new Mumbo();
    public Jumbo jumbo = new Jumbo();
    class Mumbo  {
        public synchronized void doIt() {
            try{
                System.out.println("inside mumbo " +     	Thread.currentThread().getName());
                wait();
                System.out.println("done");
            } catch (Exception e) {}
        }
    }
    class Jumbo extends Thread {
        public void run() {
            mumbo.doIt();
        }
    }
    public WaitNotify() throws Exception {
        jumbo.start();
        System.out.println( Thread.currentThread().getName() + " before sleep ");
        Thread.sleep(1000);
        synchronized (mumbo) {mumbo.notifyAll();}
    }
    public static void main(String[] args)throws Exception {
        new WaitNotify();
    }
}

