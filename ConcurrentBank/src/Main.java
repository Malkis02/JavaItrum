public class Main {
    public static void main(String[] args) {
        ConcurrentBank bank = new ConcurrentBank();

        // Создание счетов
        BankAccount account1 = bank.createAccount(1000);
        BankAccount account2 = bank.createAccount(500);

        // Перевод между счетами
        Thread transferThread1 = new Thread(() -> {
            bank.transfer(account1, account2, 200);
            try{
                Thread.sleep(200);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });
        Thread transferThread2 = new Thread(() -> {
            bank.transfer(account2, account1, 100);
            try{
                Thread.sleep(200);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        Thread transferThread3 = new Thread(() -> {
            account1.deposit(135);
            try{
                Thread.sleep(200);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
            }
        });

        transferThread1.start();
        transferThread2.start();
        transferThread3.start();
        try {
            transferThread1.join();
            transferThread2.join();
            transferThread3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }

        System.out.println("End------------");
        System.out.println(account1.getBalance());
        System.out.println(account2.getBalance());
        // Вывод общего баланса
        System.out.println("Total balance: " + bank.getTotalBalance());
    }
}