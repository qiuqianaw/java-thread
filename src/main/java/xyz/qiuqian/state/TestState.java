package xyz.qiuqian.state;

/**
 * 观察测试线程状态
 * @author qiuqian
 */
public class TestState {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread( () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("--------------");
        } );

        // 观察状态
        Thread.State state = thread.getState();
        // NEW
        System.out.println(state);

        // 观察启动后
        thread.start();
        state = thread.getState();
        // RUN
        System.out.println(state);

        // 只要线程不终止，一直输出状态
        while ( state != Thread.State.TERMINATED ) {
            Thread.sleep(100);
            // 更新线程状态
            state = thread.getState();
            System.out.println(state);
        }

        // 停止后不能再运行！测试：
        thread.start();

    }
}
