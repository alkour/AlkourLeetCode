package myExamples.concurrency;

public class WaitNotifyCalculator {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        calculator.start();
        Reader r1 = new Reader(calculator);
        Reader r2  = new Reader(calculator);
        Reader r3  = new Reader(calculator);
        Reader r4  = new Reader(calculator);

        r1.start(); r2.start(); r3.start(); r4.start();

        try {
            r1.join();
            r2.join();
            r3.join();
            r4.join();
        } catch (InterruptedException ex) {
            System.out.println("Can not join");
        }


        try {
            calculator.interrupt();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}

class Reader extends Thread {
    Calculator calc;
    public Reader(Calculator calc) {
        this.calc =  calc;
    }

    @Override
    public void run() {
        synchronized (calc) {
            try {
                calc.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Calculated. Sum = " + calc.sum);
        }
    }
}

class Calculator extends Thread {
    int sum;
    @Override
    public void run() {
        while (true ) {
//            if (Thread.currentThread().interrupted()) {
//                System.out.println("Calculator interupted");
//                break;
//                throw new RuntimeException("Calculaton interupted");
//            }
//            if (isInterrupted()) {
//                System.out.println("Calculator interupted");
//                break;
//            }
            synchronized (this) {
                sum = 0;
                for (int i = 0; i < 10 ; i++) {
                    sum += i;
                }
                notifyAll();
            }
        }
    }
}
