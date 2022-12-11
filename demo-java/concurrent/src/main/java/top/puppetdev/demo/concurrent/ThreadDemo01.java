package top.puppetdev.demo.concurrent;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022/7/25 11:34
 */
@Slf4j
public class ThreadDemo01 {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread() {
            @Override
            public void run() {
                log.info("start");
                while (true) {
                }
                // 因为前面定义了一个死循环，所以下面的语句不会被执行
                // log.info("end");
            }
        };
        thread.setName("thread");
        thread.start();
        
        // 当前线程休眠 1 秒
        TimeUnit.SECONDS.sleep(1);
        thread.stop();
        // 输出线程 thread 的状态
        log.info("{}", thread.getState());
        
        
        // 当前线程休眠 1 秒
        TimeUnit.SECONDS.sleep(1);
        // 输出线程 thread 的状态
        log.info("{}", thread.getState());
    }
    
}
