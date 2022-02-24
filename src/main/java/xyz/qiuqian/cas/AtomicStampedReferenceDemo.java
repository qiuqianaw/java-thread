package xyz.qiuqian.cas;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicStampedReferenceDemo {
    public static void main(String[] args) {
        // 如果范性是包装类，注意对象的引用问题
        AtomicStampedReference<Integer> integerAtomicStampedReference = new AtomicStampedReference<>(1, 1);

        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();// 获取版本号
            System.out.println("A1 => " + stamp);
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // 乐观锁每次执行完成后会把 version + 1
            System.out.println(integerAtomicStampedReference.compareAndSet(1,
                    2,
                    integerAtomicStampedReference.getStamp(),
                    integerAtomicStampedReference.getStamp() + 1
            ));
            System.out.println("A2 => " + integerAtomicStampedReference.getStamp());

            System.out.println(integerAtomicStampedReference.compareAndSet(2,
                    1,
                    integerAtomicStampedReference.getStamp(),
                    integerAtomicStampedReference.getStamp() + 1
            ));
            System.out.println("A3 => " + integerAtomicStampedReference.getStamp());

        }, "A").start();


        // 和乐观锁原理相同
        new Thread(() -> {
            int stamp = integerAtomicStampedReference.getStamp();// 获取版本号
            System.out.println("B1 => " + stamp);

            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(integerAtomicStampedReference.compareAndSet(1, 6, stamp, stamp + 1));

            System.out.println("B1 => " + integerAtomicStampedReference.getStamp());
        }, "B").start();

    }
}
