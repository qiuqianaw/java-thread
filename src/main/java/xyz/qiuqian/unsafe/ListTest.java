package xyz.qiuqian.unsafe;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

// java.util.ConcurrentModificationException 并发修改异常
public class ListTest {
    public static void main(String[] args) {
        // 并发下 ArrayList 不安全
        /**
         * 解决方案
         *  1. vector
         *  2. List<String> list = Collections.synchronizedList(new ArrayList<>());
         *  3. List<String> list = new CopyOnWriteArrayList<>();
         */

        // CopyOnWrite 写入时复制 COW 计算机程序设计时的一种优化策略
        // 多个线程调用的时候，list读取的时候，固定的，写入（覆盖）
        // 在写入的时候避免覆盖，造成数据问题
        // CopyOnWriteArrayList 比 Vector 好在哪里？ 1. synchronized 效率低，写入时复制是lock锁

        List<String> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 10; i++) {
            new Thread(()-> {
                list.add(UUID.randomUUID().toString().substring(0, 5));
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
