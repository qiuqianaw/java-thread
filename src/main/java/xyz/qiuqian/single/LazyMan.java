package xyz.qiuqian.single;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

/**
 * 什么玩意儿，没听懂=。=
 */

// 懒汉式单例
public class LazyMan {

    private static boolean qiuqian = false;

    private LazyMan() {

        synchronized (LazyMan.class) {
            if ( qiuqian == false ) {
                qiuqian = true;
            } else {
                throw new RuntimeException("不要试图通过反射破坏异常");
            }
        }

        System.out.println(Thread.currentThread().getName() + " => OK");
    }

    // volatile 避免指令重排！
    private volatile static LazyMan lazyMan;

    // 双重检测锁模式的懒汉式单例，DCL懒汉式
    public static LazyMan getInstance() {
        if ( lazyMan == null ) {
            synchronized (LazyMan.class) {
                if ( lazyMan == null ) {
                    lazyMan = new LazyMan(); // 不是原子性操作
                    /**
                     * 1 分配内存空间
                     * 2 执行构造方法，初始化对象
                     * 3 把这个对象指向这个空间
                     */
                }
            }
        }
        return lazyMan; // 此时 lazyMan还没有完成构造
    }

    // 但线程下单例没有问题，并发下就不行了

//    public static void main(String[] args) {
//        for (int i = 0; i < 10; i++) {
//            new Thread(() -> {
//                lazyMan.getInstance();
//            }).start();
//        }
//    }

    // 反射！
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {
//        LazyMan instance = LazyMan.getInstance();

        Field qiuqian = LazyMan.class.getDeclaredField("qiuqian");
        // 破坏私有权限
        qiuqian.setAccessible(true);

        Constructor<LazyMan> declaredConstructor = LazyMan.class.getDeclaredConstructor(null);
        declaredConstructor.setAccessible(true);

        LazyMan instance = declaredConstructor.newInstance();

        qiuqian.set(instance, false);
        LazyMan lazyMan = declaredConstructor.newInstance();

        System.out.println(instance);
        System.out.println(lazyMan);

    }

}
