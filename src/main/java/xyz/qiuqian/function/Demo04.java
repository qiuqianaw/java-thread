package xyz.qiuqian.function;

import java.util.function.Supplier;

/**
 * Supplier 供给型接口
 */
public class Demo04 {
    public static void main(String[] args) {
        Supplier supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return 1024;
            }
        };

        Supplier supplier1 = () -> {return 1024;};

        System.out.println(supplier.get());
        System.out.println(supplier1.get());
    }
}
