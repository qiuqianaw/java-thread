package xyz.qiuqian.lock;

import java.util.concurrent.locks.ReentrantLock;

// 显式定义锁
public class TestLock {
    public static void main(String[] args) {
        TestLock2 testLock2 = new TestLock2();

        new Thread(testLock2).start();
        new Thread(testLock2).start();
        new Thread(testLock2).start();
    }
}

class TestLock2 implements Runnable {
    int ticketNum = 10;

    // 定义lock锁
    private ReentrantLock lock = new ReentrantLock();

    @Override
    public void run() {
        while ( true ) {
            try {
                lock.lock(); // 加锁
                if ( ticketNum >= 0 ) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(ticketNum--);
                } else {
                    break;
                }
            } finally {
                lock.unlock(); // 解锁
            }

        }
    }
}
