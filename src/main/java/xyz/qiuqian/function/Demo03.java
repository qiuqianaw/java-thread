package xyz.qiuqian.function;

import java.util.function.Consumer;

/**
 *
 */
public class Demo03 {
    public static void main(String[] args) {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String str) {
                System.out.println(str);
            }
        };

        Consumer<String> consumer1 = str -> {
            System.out.println(str);
        };

        consumer.accept("qiuqian");
        consumer1.accept("aowu");
    }
}
