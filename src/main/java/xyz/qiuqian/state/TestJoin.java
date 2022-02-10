package xyz.qiuqian.state;

/**
 * 测试强制执行
 * @author qiuqian
 */
public class TestJoin implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("线程vip.." + i);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // 启动线程
        TestJoin testJoin = new TestJoin();
        Thread thread = new Thread(testJoin);
        thread.start();

        // 主线程
        for (int i = 0; i < 500; i++) {
            if ( i == 200 ) {
                // 插队
                Thread.sleep(10);
                thread.join();
            }
            System.out.println("main.." + i);
        }
    }
}
