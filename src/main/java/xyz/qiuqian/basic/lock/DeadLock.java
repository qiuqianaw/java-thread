package xyz.qiuqian.basic.lock;

// 死锁：多个线程互相持有对方需要的资源，形成僵持
public class DeadLock {
    public static void main(String[] args) {
        MakeUp makeUp1 = new MakeUp(0, "ao");
        MakeUp makeUp2 = new MakeUp(1, "wu");

        makeUp1.start();
        makeUp2.start();
    }
}

// 口红
class Lipstick {

}

// 镜子
class Mirror {

}

class MakeUp extends Thread {

    // 需要的资源只有一份，用 static 来保证只有一份
    static Lipstick lipstick = new Lipstick();
    static Mirror mirror = new Mirror();

    int choice; // 选择
    String name; // 使用者

    MakeUp(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    @Override
    public void run() {
        // 化妆
        try {
            makeup();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 互相持有对方的锁，就是需要拿到对方的资源
    private void makeup() throws InterruptedException {
        if ( choice == 0 ) {
            // 获得口红的锁
            synchronized (lipstick) {
                System.out.println(this.name + "获得口红的锁");
                Thread.sleep(1000);
            }
            synchronized (mirror) { // 1秒钟后获得镜子的锁
                System.out.println(this.name + "获得镜子的锁");
            }
        } else {
            synchronized (mirror) {
                System.out.println(this.name + "获得镜子的锁");
                Thread.sleep(2000);
            }
            synchronized (lipstick) { // 1秒钟后获得镜子的锁
                System.out.println(this.name + "获得口红的锁");
            }
        }

        // deadlock
//        if ( choice == 0 ) {
//            // 获得口红的锁
//            synchronized (lipstick) {
//                System.out.println(this.name + "获得口红的锁");
//                Thread.sleep(1000);
//                synchronized (mirror) { // 1秒钟后获得镜子的锁
//                    System.out.println(this.name + "获得镜子的锁");
//                }
//            }
//        } else {
//            synchronized (mirror) {
//                System.out.println(this.name + "获得镜子的锁");
//                Thread.sleep(2000);
//                synchronized (lipstick) { // 1秒钟后获得镜子的锁
//                    System.out.println(this.name + "获得口红的锁");
//                }
//            }
//        }
    }
}