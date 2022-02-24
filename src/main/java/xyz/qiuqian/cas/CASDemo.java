package xyz.qiuqian.cas;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * CAS: compare and set 比较并交换
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(2022);

        /**
         *     public final boolean compareAndSet(int expect, int update) {
         *         return unsafe.compareAndSwapInt(this, valueOffset, expect, update);
         *     }
         */
        // 如果拿到了期望值，就更新，否则不更新
        // CAS 是 CPU 的并发原语
        atomicInteger.compareAndSet(2022, 2023);
        System.out.println(atomicInteger.get());

        /**
         *     public final int getAndIncrement() {
         *         return unsafe.getAndAddInt(this, valueOffset, 1);
         *     }
         *     unsafe: Java 无法操纵内存，但可以调用cpp操作内存
         *     相当于Java的后门，通过这个类操作内存
         *
         */

        /**
         *     public final int getAndAddInt(Object var1, long var2, int var4) {
         *         int var5;
         *         do {
         *             var5 = this.getIntVolatile(var1, var2);
         *         } while(!this.compareAndSwapInt(var1, var2, var5, var5 + var4));
         *
         *         return var5;
         *     }
         *
         *     自旋锁
         */
        atomicInteger.getAndIncrement();// ++

        // 修改失败，返回false
        System.out.println(atomicInteger.compareAndSet(2022, 2023));

    }
}
