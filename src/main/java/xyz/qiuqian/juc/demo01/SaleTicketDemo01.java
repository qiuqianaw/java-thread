package xyz.qiuqian.juc.demo01;

// 卖票demo

/**
 * 真正的多线程呢开发，线程就是一个单独的资源类，没有任何附属操作
 * 包含：属性，方法
 */
public class SaleTicketDemo01 {
    public static void main(String[] args) {
        // 并发：多线程操作同一个资源类
        Ticket ticket = new Ticket();

        // 函数式接口
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A").start();
        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B").start();
        new Thread(() -> {
//            for (int i = 0; i < 60; i++) {
//                ticket.sale();
//            }
        }, "C").start();
    }
}

// 资源类 OOP面向对象编程
class Ticket {
    private int ticketNum = 50;

    // synchronized 本质就是排队
    public synchronized void sale() {
        if ( ticketNum > 0 ) {
//            ticketNum--;
            System.out.println(Thread.currentThread().getName() + "卖出了第" + ticketNum-- + "张票，剩下" + ticketNum + "张");
        }
    }
}