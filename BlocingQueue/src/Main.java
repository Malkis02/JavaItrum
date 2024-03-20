public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);

        Thread threadProduce = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingQueue.enqueue(i);
                    System.out.println("Task add " + i);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread threadConsume = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    blockingQueue.dequeue();
                    Thread.sleep(1000);
                    System.out.println("Task end " + i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        threadProduce.start();
        threadConsume.start();
    }
}