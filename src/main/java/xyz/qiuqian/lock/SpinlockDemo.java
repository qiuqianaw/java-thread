package xyz.qiuqian.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 自旋锁
 */
public class SpinlockDemo {

    AtomicReference<Thread> threadAtomicReference = new AtomicReference<>();

    // 加锁
    public void myLock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ===> myLock");

        // 自旋锁
        while(!threadAtomicReference.compareAndSet(null, thread)) {

        }
    }

    // 解锁
    public void myUnlock() {
        Thread thread = Thread.currentThread();
        System.out.println(Thread.currentThread().getName() + " ===> myUnlock");
        threadAtomicReference.compareAndSet(thread, null);
    }


}
