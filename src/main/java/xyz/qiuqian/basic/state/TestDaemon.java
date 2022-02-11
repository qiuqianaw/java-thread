package xyz.qiuqian.basic.state;

/**
 * 测试守护线程
 * @author qiuqian
 */
public class TestDaemon {
    public static void main(String[] args) {
        God god = new God();
        Human human = new Human();

        Thread thread = new Thread(god);
        // 默认为false， 表示为用户线程， 正常的线程都是用户线程
        thread.setDaemon(true);
        // 守护线程启动(vm不用等待守护线程进行完毕)
        thread.start();

        // 用户线程启动
        new Thread(human).start();
    }
}

class God implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println("god bless you...");
        }
    }
}

class Human implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 36500; i++) {
            System.out.println("live happy~");
        }
        System.out.println("=============good bye, world=============");
    }
}