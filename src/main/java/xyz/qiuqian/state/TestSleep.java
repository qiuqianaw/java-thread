package xyz.qiuqian.state;

import xyz.qiuqian.demo.demo01.TestThread4;

/**
 * 模拟网络延时
 *  放大问题的发生性
 *
 * @author qiuqian
 */
public class TestSleep implements Runnable {

    private int ticket_num = 10;

    @Override
    public void run() {
        while (true) {
            if (ticket_num <= 0) {
                break;
            }
            try {
                // 模拟延时
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "--> 拿到了第" + ticket_num-- + "张票");
        }
    }

    public static void main(String[] args) {
        TestThread4 ticket = new TestThread4();

        new Thread(ticket, "qiuqian").start();
        new Thread(ticket, "liuyangzhu").start();
        new Thread(ticket, "aowu").start();
    }
}
