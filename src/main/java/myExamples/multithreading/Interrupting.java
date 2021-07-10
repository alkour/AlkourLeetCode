package myExamples.multithreading;

public class Interrupting extends Thread {
    public static void main( String[] args) throws Exception {
        Interrupting t = new Interrupting ();
        t.start();
        Thread.sleep( 1000);
        t.interrupt();
        Thread.sleep( 1000);
        t.interrupt();
        if( Thread.interrupted()) {
            System.out.print(" 3");
        }
    }
    public void run() {
        while(! Thread.interrupted()) {
            try { Thread.sleep( 500);
            } catch( InterruptedException e) {
                System.out.print(" 1");
            }
        }
        System.out.print(" 2");
    }
}

