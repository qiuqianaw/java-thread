package xyz.qiuqian.basic.syn;

/**
 * 不安全取钱
 *
 * @author qiuqian
 */
public class UnsafeBank {
    public static void main(String[] args) {
        Account account = new Account(10000, "qiuqian's Funding");
        Drawing you = new Drawing(account, 50, "你");
        Drawing friend = new Drawing(account, 100, "friend");

        you.start();
        friend.start();
    }
}

class Account {
    int money;
    String name;

    public Account(int money, String name) {
        this.money = money;
        this.name = name;
    }
}

class Drawing extends Thread {
    Account account;
    int drawing_money;
    int deposit;

    public Drawing(Account account, int drawing_money, String name) {
        super(name);
        this.account = account;
        this.drawing_money = drawing_money;
    }

    @Override
    public void run() {
        // synchronized 块
        synchronized (account) {
            if ( account.money - drawing_money < 0 ) {
                System.out.println(Thread.currentThread().getName() + "钱不够，取不了");
                return;
            }
            // 模拟延时，放大问题发生
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            account.money -= drawing_money;
            deposit += drawing_money;

            System.out.println(account.name + "余额为：" + account.money);
            System.out.println(Thread.currentThread().getName() + "手里的钱：" + deposit);
        }

    }
}