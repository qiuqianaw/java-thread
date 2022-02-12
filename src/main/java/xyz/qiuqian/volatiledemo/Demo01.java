package xyz.qiuqian.volatiledemo;

import java.util.concurrent.TimeUnit;

public class Demo01 {
    // 不加 volatile 程序会死循环
    // 加 volatile 可以保证可见性
    private volatile static int num = 0;
    public static void main(String[] args) {
        new Thread(() -> {
            while ( num == 0 ) {

            }
        }).start();

        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        num = 1;
        System.out.println(num);
    }
}
