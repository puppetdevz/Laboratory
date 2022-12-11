package top.puppetdev.demo.concurrent;

/**
 * @author puppet
 * @since 2022/7/29 17:05
 */
public class Demo01Volatile {
    public volatile static boolean flag = true;
    
    public static class T1 extends Thread {
        public T1(String name) {
            super(name);
        }
        
        @Override
        public void run() {
            System.out.println("Thread " + this.getName() + " started");
            while (flag) {
            }
            System.out.println("Thread " + this.getName() + " stopped");
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        new T1("t1").start();
        // 休眠 1 秒，便于观察
        Thread.sleep(1000);
        //将 flag 置为 false
        flag = false;
    }
}
