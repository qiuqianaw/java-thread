package xyz.qiuqian.unsafe;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * java.util.ConcurrentModificationException
 */
public class MapTest {
    public static void main(String[] args) {
        // map 是这样用的吗？ 不是
        // 默认等价与什么？ new HashMap<>(16, 0.75)
        //Map<String, String> map = new HashMap<>();
        // 加载因子，初始化容量

        // 解决方案
        Map<String, String> map = new ConcurrentHashMap<>();

        for (int i = 0; i < 300; i++) {
            new Thread(()-> {
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 5));
                System.out.println(map);
            }, String.valueOf(i)).start();
        }
    }
}
