package xyz.qiuqian.basic.lambda;

/**
 * 推导lambda表达式
 * @author qiuqian
 */
public class TestLambda1 {

    // 3.静态内部类
    static class Like2 implements ILike {
        @Override
        public void lambda() {
            System.out.println("hello, lambda2!静态内部类");
        }
    }


    public static void main(String[] args) {
        ILike iLike = new Like();
        iLike.lambda();

        iLike = new Like2();
        iLike.lambda();

        // 4.局部内部类
        class Like3 implements ILike {
            @Override
            public void lambda() {
                System.out.println("hello, lambda3!局部内部类");
            }
        }
        iLike = new Like3();
        iLike.lambda();

        // 5.匿名内部类,没有类的名称，必须借助接口或夫类
        iLike = new ILike() {
            @Override
            public void lambda() {
                System.out.println("hello, lambda4!匿名内部类");
            }
        };
        iLike.lambda();

        // 6.lambda简化
        iLike = () -> {
            System.out.println("hello, lambda5!");
        };
        iLike.lambda();
    }

}

// 1.定义一个函数式接口
interface ILike{
    void lambda();
}

// 2.实现类
class Like implements ILike {
    @Override
    public void lambda() {
        System.out.println("hello, lambda!");
    }
}