package xyz.qiuqian.juc.demo01;

// 卖票demo

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 真正的多线程呢开发，线程就是一个单独的资源类，没有任何附属操作
 * 包含：属性，方法
 */
public class SaleTicketDemo02 {
    public static void main(String[] args) {
        Ticket2 ticket = new Ticket2();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "A").start();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "B").start();

        new Thread(()-> {
            for (int i = 0; i < 40; i++) ticket.sale();
        }, "C").start();

    }
}

// 资源类 OOP面向对象编程
class Ticket2 {
    private int ticketNum = 30;

    Lock lock = new ReentrantLock();

    public void sale() {
        lock.lock();


        try {
            // 业务代码在try里面
            if ( ticketNum > 0 ) {
                System.out.println(Thread.currentThread().getName() + "卖出了第" + ticketNum-- + "张票，剩下" + ticketNum + "张");
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }


    }
}