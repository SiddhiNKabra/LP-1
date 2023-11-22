import java.util.concurrent.*;

public class semaphore{
    private static BlockingQueue<Integer> buffer = new LinkedBlockingDeque<>();
    private static Semaphore empty = new Semaphore(10);
    private static Semaphore full = new Semaphore(0);
    public static void main(String[] args){
        Thread producerThread = new Thread(new producer());
        Thread consumerThread = new Thread(new consumer());

        producerThread.start();
        consumerThread.start();
    }

    static class producer implements Runnable{
        @Override

        public void run(){
            try{
                for(int i = 0; i < 20; i++){
                    int item = produceitem();
                    empty.acquire();
                    buffer.put(item);
                    System.out.println("Produced: " + item);
                    full.release();
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        private int produceitem(){
            return (int) (Math.random() * 100);
        }
    }
    static class consumer implements Runnable{
        @Override
        public void run(){
            try{
                for(int i = 0; i < 20; i++){
                    full.acquire();
                    int item = buffer.take();
                    empty.release();
                    System.out.println("Consumed: " + item);
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}