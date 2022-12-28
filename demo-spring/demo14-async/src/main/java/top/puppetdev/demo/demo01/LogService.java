package top.puppetdev.demo.demo01;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-12-28 下午 10:37
 */
@Component
public class LogService {
    @Async
    public void log(String msg) throws InterruptedException {
        System.out.println(Thread.currentThread() + "开始记录日志，" + System.currentTimeMillis());
        // 模拟耗时两秒
        TimeUnit.SECONDS.sleep(2);
        System.out.println(Thread.currentThread() + "日志记录完毕，" + System.currentTimeMillis());
    }
}
