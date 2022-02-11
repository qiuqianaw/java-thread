package xyz.qiuqian.basic.lambda;

/**
 * @author qiuqian
 */
public class TestLabmda2 {

    public static void main(String[] args) {
        // 简化表达式
        Labmda labmda = param -> {
            System.out.println("hello, labmda --> " + param);
        };

        // 简化参数类型
        labmda = param -> {
            System.out.println("hello, labmda2 --> " + param);
        };

        // 简化{}，只针对只有一行代码的情况
        labmda = param -> System.out.println("hello, labmda3 --> " + param);

        labmda.print("there is a param");
    }

}

interface Labmda {
    void print(String param);
}

