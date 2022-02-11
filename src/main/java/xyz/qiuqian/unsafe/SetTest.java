package xyz.qiuqian.unsafe;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 同理可得 java.util.ConcurrentModificationException 并发修改异常
 *
 */
public class SetTest {
    public static void main(String[] args) {
        // 出错

        // HashSet 底层就是 HashMap
        Set<String> set = new HashSet<>();

        // 解决方案1， 集合类转为安全
//        Set<String> set = Collections.synchronizedSet(new HashSet<>());

        // 解决方案2， CopyOnWrite
//        Set<String> set = new CopyOnWriteArraySet<>();


        for (int i = 0; i < 30; i++) {
            new Thread(()-> {
                set.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
