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
 *  5. 增加两个静态的同步方法,只有一个对象，两个线程是先发短信还是先打电话？
 *      static静态方法，类一加载就有了，锁的是Class对象！两个方法都被static修饰，拿到的都是同一个锁。可以再加一个Phone对象测试
 *  6. 增加两个静态的同步方法,两对象，两个线程是先发短信还是先打电话？
 *      static静态方法，类一加载就有了，锁的是Class对象！两个方法都被static修饰，拿到的都是同一个锁。和5类似
 *  7. 一个静态同步方法，一个普通同步方法，只有一个对象，两个线程是先发短信还是先打电话？
 *      一个锁的是class模版，一个锁的是调用者，是两个不同的锁
 *  8. 一个静态同步方法，一个普通同步方法，两个个对象，两个线程是先发短信还是先打电话？
 *      和6类似，是两个不同的锁
 */
public class Test4 {
    public static void main(String[] args) {
        // 两个对象，两个调用者，拿到的是两个不同的锁，按照时间顺序执行！
        Phone4 phone1 = new Phone4();
        Phone4 phone2 = new Phone4();

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


class Phone4 {
    // synchronized 锁的对象是方法的调用者
    // 两个方法用同一个锁，谁先拿到锁谁先执行！

    // 静态同步方法
    public static synchronized void sendMsg() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("sendMsg");
    }


    // 普通同步方法
    public synchronized void call() {
        System.out.println("call");
    }

}
