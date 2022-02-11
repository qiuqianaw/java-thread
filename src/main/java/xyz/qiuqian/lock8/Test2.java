package xyz.qiuqian.lock8;

import java.util.concurrent.TimeUnit;

/**
 * 关于锁的8个问题
 *  1. 标准情况下，两个线程是先发短信还是先打电话？
 *  2. 发短信的方法延迟4秒，两个线程是先发短信还是先打电话？
 *      synchronized 锁的对象是方法的调用者，两个方法用同一个锁，谁先拿到锁谁先执行！
 *  3. 增加了一个普通方法后，先打印发短信还是hello？
 *      普通方法hello，没有锁，不是同步方法，不受锁的影响！
 *  4. 两个对象，两个同步方法，先执行发短信还是打电话？
 *      两个对象，两个调用者，拿到的是两个不同的锁，按照时间顺序执行！
 */
public class Test2 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，拿到的是两个不同的锁，按照时间顺序执行！
        Phone2 phone1 = new Phone2();
        Phone2 phone2 = new Phone2();

        // 锁的存在
        new Thread(()-> {
            phone1.sendMsg();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(()-> {
            phone2.call();
        }, "B").start();
    }
}

class Phone2 {
    // synchronized 锁的对象是方法的调用者
    // 两个方法用同一个锁，谁先拿到锁谁先执行！

    public synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendMsg");
    }

    public synchronized void call() {
        System.out.println("call");
    }

    // 没有锁，不是同步方法，不受锁的影响！
    public void hello() {
        System.out.println("hello");
    }
}
