package xyz.qiuqian.demo.demo01;

/**
 * 创建线程方式1
 * 继承Thread类
 * 重写run()
 * 调用start()
 *
 * @author qiuqian
 */
public class TestThread1 extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("run()---" + i);
        }
    }


    public static void main(String[] args) {
        TestThread1 testThread1 = new TestThread1();
        // 调用start()方法启动线程
        testThread1.start();

        for (int i = 0; i < 10; i++) {
            System.out.println("我是main()方法---" + i);
        }
    }
}
