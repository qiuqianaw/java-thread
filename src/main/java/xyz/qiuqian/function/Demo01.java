package xyz.qiuqian.function;

import java.util.function.Function;

/**
 * Function 函数型接口
 *
 * 特性：
 *  一个输入参数，一个输出
 *  只要是函数式接口，就可以用 lambda 表达式简化
 */
public class Demo01 {
    public static void main(String[] args) {
        // 匿名内部类
        // 工具类，输出输入的值
//        Function function = new Function<String, String>() {
//            @Override
//            public String apply(String o) {
//                return o;
//            }
//        };

//        Function<String, String> function = (str) -> {
//            return str;
//        };

        Function<String, String> function = str -> {return str;};

        System.out.println(function.apply("123"));
    }
}
