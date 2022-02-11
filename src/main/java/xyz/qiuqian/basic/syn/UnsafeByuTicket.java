package xyz.qiuqian.basic.syn;

/**
 * 不安全的买票
 *  线程不安全，有负数
 * @author qiuqian
 */
public class UnsafeByuTicket {
    public static void main(String[] args) {
        BuyTicket station = new BuyTicket();

        new Thread(station, "Java").start();
        new Thread(station, "Golang").start();
        new Thread(station, "C++").start();

    }
}

class BuyTicket implements Runnable {

    private int ticket_nums = 10;
    private boolean flag = true;

    // 加锁
    private synchronized void buy() throws InterruptedException {
        if ( ticket_nums <= 0 ) {
            flag = false;
            return;
        }
        // 模拟延时
//        Thread.sleep(100);
        System.out.println(Thread.currentThread().getName() + "拿到" + ticket_nums--);
    }

    @Override
    public void run() {
        // 买票
        while (flag) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                buy();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}