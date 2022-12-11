package top.puppetdev.demo.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-07-29 下午 09:16
 */
public class ThreadGroupDemo01 {
    public static class R implements Runnable {
        @Override
        public void run() {
            System.out.println("threadName: " + Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadGroup threadGroup = new ThreadGroup("thread-group-1");
        Thread t1 = new Thread(threadGroup, new R(), "t1");
        Thread t2 = new Thread(threadGroup, new R(), "t2");
        t1.start();
        t2.start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("活动线程数: " + threadGroup.activeCount());
        System.out.println("活动线程组: " + threadGroup.activeGroupCount());
        System.out.println("线程组名称: " + threadGroup.getName());
    }
}
