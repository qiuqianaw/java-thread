package xyz.qiuqian.basic.demo.demo01;

/**
 * 案例：龟兔赛跑
 * @author qiuqian
 */
public class Race implements Runnable{
    private static String winner;
    @Override
    public void run() {
        for (int i = 1; i <= 100; i++) {
            // 模拟兔子休息
            if ( Thread.currentThread().getName().equals("兔子") ) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    Thread.sleep(8);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            boolean gameOver = isGameOver(i);
            if ( gameOver ) {
                break;
            }
            System.out.println(Thread.currentThread().getName() + "-->跑了" + i + "步");
        }
    }

    boolean isGameOver(int steps) {
        if ( winner != null ) {
            return true;
        } else {
            if ( steps >= 100 ) {
                winner = Thread.currentThread().getName();
                System.out.println("winner is: " + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Race race = new Race();
        new Thread(race, "兔子").start();
        new Thread(race, "乌龟").start();
    }
}
