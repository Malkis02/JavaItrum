import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ConcurrentBank{

    private Map<Long,BankAccount> listAccount = new HashMap<>();

    private long customIdCounter = 0;

    public synchronized void nextId() {
        customIdCounter++;
    }

    BankAccount createAccount(int balance){
        BankAccount account = new BankAccount(balance);
        listAccount.put(customIdCounter,account);
        nextId();
        return account;
    }


    public synchronized void transfer(BankAccount acc1,BankAccount acc2,int amount){
        if(acc1.getBalance() - amount <= 0){
            System.out.println("Недостаточно средств");
        }else {
            acc1.setBalance(acc1.getBalance() - amount);
            acc2.setBalance(acc2.getBalance() + amount);
        }
    }

    public synchronized Optional<Double> getTotalBalance(){
        return listAccount.values().parallelStream().map(BankAccount::getBalance).reduce(Double::sum);
    }

//    @Override
//    public void run() {
//        Thread.yield();
//    }
}
