package xyz.qiuqian.demo.demo02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * 创建方式3：实现Callable接口
 *  实现Callable接口，需要返回值类型
 *  重写call方法，需要抛出异常
 *  创建目标对象
 *  执行创建服务
 *  提交执行
 *  获取结果
 *  关闭服务
 * @author qiuqian
 */
public class TestCallable implements Callable<Boolean> {
    private String url;
    private String name;

    public TestCallable(String url, String name) {
        this.url = url;
        this.name = name;
    }

    @Override
    public Boolean call() {
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.webDownloader(url, name);
        System.out.println("download success: " + name);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        TestCallable t1 = new TestCallable("https://picbedd.oss-cn-beijing.aliyuncs.com/image-20210915230151752.png", "1--qiuqian.png");
        TestCallable t2 = new TestCallable("https://picbedd.oss-cn-beijing.aliyuncs.com/image-20210915005902838.png", "2--lanqiao.png");
        TestCallable t3 = new TestCallable("https://picbedd.oss-cn-beijing.aliyuncs.com/image-20210914224825674.png", "3--book.png");

        // 创建执行服务
        ExecutorService service = Executors.newFixedThreadPool(3);
        // 提交执行
        Future<Boolean> r1 = service.submit(t1);
        Future<Boolean> r2 = service.submit(t2);
        Future<Boolean> r3 = service.submit(t3);
        // 获取结果
        boolean rs1 = r1.get();
        boolean rs2 = r2.get();
        boolean rs3 = r3.get();

        System.out.println(rs1);
        System.out.println(rs2);
        System.out.println(rs3);
        // 关闭服务
        service.shutdownNow();
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