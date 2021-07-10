package myExamples.concurrency.depositwithdraw;

import static java.lang.String.*;

public class BankAccount {
    public static void main(String[] args) {

        final Customer c = new Customer(1000);
        new Thread(() -> {c.withdraw(1500);}).start();
        new Thread(() -> {c.deposit(1000);}).start();

    }
}

class  Customer {
    int amount;
    Customer(int initialAmount) {
        amount = initialAmount;
    }


    synchronized void withdraw(int amount){
        System.out.println("going to withdraw...");

        if(this.amount<amount){
            System.out.println(format("Thread = %s; Less balance; waiting for deposit...", Thread.currentThread().getName()));
//            System.out.println("Less balance; waiting for deposit...");
            try{wait();}catch(Exception e){}
        }
        this.amount-=amount;
        System.out.println(format("Thread = %s; withdraw completed...", Thread.currentThread().getName()));
//        System.out.println("withdraw completed...");
    }

    synchronized void deposit(int amount){
        System.out.println(format("Thread = %s; going to deposit...", Thread.currentThread().getName()));
//        System.out.println("going to deposit...");
        this.amount+=amount;
        System.out.println(format("Thread = %s; deposit completed... ", Thread.currentThread().getName()));
//        System.out.println("deposit completed... ");
        notify();
    }

}

