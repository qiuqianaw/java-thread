package xyz.qiuqian.demo.demo01;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * 实现多线程同步下载图片
 * @author qiuqian
 */
public class TestThread2 extends Thread {
    private String url;
    private String name;

    public TestThread2(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public void run() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.webDownloader(url, name);
        System.out.println("download success: " + name);
    }

    public static void main(String[] args) {
        TestThread2 t1 = new TestThread2("https://picbedd.oss-cn-beijing.aliyuncs.com/image-20210915230151752.png", "1--qiuqian.png");
        TestThread2 t2 = new TestThread2("https://picbedd.oss-cn-beijing.aliyuncs.com/image-20210915005902838.png", "2--lanqiao.png");
        TestThread2 t3 = new TestThread2("https://picbedd.oss-cn-beijing.aliyuncs.com/image-20210914224825674.png", "3--book.png");

        t1.start();
        t2.start();
        t3.start();
    }
}

class WebDownloader{
    public void webDownloader(String url, String name) {
        try {
            FileUtils.copyURLToFile(new URL(url), new File(name));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("IO error---webDownloader()");
        }
    }
}