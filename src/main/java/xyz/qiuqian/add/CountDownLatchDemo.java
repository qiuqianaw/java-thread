package xyz.qiuqian.add;

import java.util.concurrent.CountDownLatch;

// 计数器
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        // 总数是6
        // 在必须要执行的任务的时候使用
        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(()-> {
                System.out.println(Thread.currentThread().getName() + "run 了");
                countDownLatch.countDown(); // -1
            }, String.valueOf(i)).start();
        }

        // 里面的顺序不关系，但是只要里面的东西都完了再执行下面的内容
        countDownLatch.await(); // 等待计数器归零，然后向下执行

        System.out.println("你给我回来!");
    }
}
