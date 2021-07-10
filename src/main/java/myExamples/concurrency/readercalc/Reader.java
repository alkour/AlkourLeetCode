package myExamples.concurrency.readercalc;

public class Reader extends  Thread {
    Calculator c;
    public Reader(Calculator c) {
        this.c = c;
    }

    public void run() {
        synchronized (c) {

            try {
                System.out.println("Waiting for calculation ...");
                c.wait();
            } catch (InterruptedException e) { }
            System.out.println("Total is: " + c.total);
        }
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        new Reader(calculator).start();
        new Reader(calculator).start();
        new Reader(calculator).start();
        new Thread(calculator).start();
    }
}
class Calculator implements Runnable {
    int total;

    public void run() {
        synchronized (this) {
            for (int i = 0; i < 100 ; i++) {
                total += i;
            }
            notifyAll();
        }

    }


}
