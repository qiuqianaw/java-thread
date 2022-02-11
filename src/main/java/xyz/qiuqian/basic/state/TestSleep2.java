package xyz.qiuqian.basic.state;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 模拟倒计时
 *
 * @author qiuqian
 */
public class TestSleep2 {
    public static void main(String[] args) {
        // 打印当前时间
        Date start_time = new Date(System.currentTimeMillis());
        while (true) {
            try {
                Thread.sleep(1000);
                System.out.println(new SimpleDateFormat("HH:mm:ss").format(start_time));
                start_time = new Date(System.currentTimeMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 模拟倒计时
     *
     * @throws InterruptedException
     */
    public static void tenDown() throws InterruptedException {
        int num = 10;

        while (true) {
            Thread.sleep(1000);
            System.out.println(num--);
            if (num <= 0) {
                break;
            }
        }
    }
}
