package xyz.qiuqian.basic.demo.demo01;

/**
 * 创建线程方式2
 *  实现runnable接口
 *  重写run()
 *  执行线程需要将线程类放入runnable接口实现类，调用start()方法
 * @author qiuqian
 */
public class TestThread3 implements Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("run()---" + i);
        }
    }


    public static void main(String[] args) {

//        TestThread3 testThread3 = new TestThread3();
//        Thread thread = new Thread(testThread3);
//        thread.start();

        // 上面的写法的等价于下面的写法
        new Thread(new TestThread3()).start();

        for (int i = 0; i < 10; i++) {
            System.out.println("我是main()方法---" + i);
        }
    }

}
