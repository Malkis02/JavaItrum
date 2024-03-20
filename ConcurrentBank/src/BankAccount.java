import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BankAccount {
    private volatile double balance;

    private final Lock lock = new ReentrantLock();

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public synchronized void deposit(double amount) {
        try {
            lock.lockInterruptibly();
            try {
                balance += amount;
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted wait");
        }
    }

    public synchronized void withdraw(double amount) {
        try {
            lock.lockInterruptibly();
            try {
                if (balance - amount < 0) {
                    System.out.println("There are insufficient funds in the account");
                } else {
                    balance -= amount;
                }
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.err.println("Interrupted wait");
        }
    }

    public synchronized double getBalance() {
        return balance;
    }

    public synchronized void setBalance(double balance) {
        this.balance = balance;
    }

    public Lock getLock() {
        return lock;
    }
}
