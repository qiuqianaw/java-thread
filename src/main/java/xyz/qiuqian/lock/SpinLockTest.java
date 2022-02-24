package xyz.qiuqian.lock;

import java.util.concurrent.TimeUnit;

public class SpinLockTest {
    public static void main(String[] args) throws InterruptedException {

        // 底层使用的自旋锁 cas
        SpinlockDemo spinlockDemo = new SpinlockDemo();

        new Thread(() -> {
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnlock();
            }

        }, "T1").start();

        TimeUnit.SECONDS.sleep(1);

        new Thread(() -> {
            spinlockDemo.myLock();
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                spinlockDemo.myUnlock();
            }
        }, "T2").start();

    }
}
