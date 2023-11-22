import java.util.concurrent.locks.*;
import java.util.*;

public class mutex{
    private static final int buffersize = 10;
    private static final Queue<Integer> buffer = new LinkedList<>();
    private static final Lock lock = new ReentrantLock();
    private static final Condition notfull = lock.newCondition();
    private static final Condition notempty = lock.newCondition();
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
                    int item = produceritem();
                    lock.lock();
                    try{
                        while(buffer.size() == buffersize){
                            notfull.await();
                        }
                        buffer.add(item);
                        System.out.println("Produced: " + item);
                        notempty.signal();
                    }finally{
                        lock.unlock();
                    }
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
        private int produceritem(){
            return (int) (Math.random() * 100);
        }
    }
    static class consumer implements Runnable{
        @Override
        public void run(){
            try{
                for(int i = 0; i < 20; i++){
                    lock.lock();
                    try{
                        while(buffer.isEmpty()){
                            notempty.await();
                        }
                        int item = buffer.poll();
                        System.out.println("Consumed: " + item);
                        notfull.signal();
                    }finally{
                        lock.unlock();
                    }
                }
            }catch(InterruptedException e){
                Thread.currentThread().interrupt();
            }
        }
    }
}