package top.puppetdev.demo.demo01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-12-28 下午 10:40
 */
@ComponentScan
@EnableAsync
public class MainConfig {
    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        LogService logService = context.getBean(LogService.class);
        System.out.println(Thread.currentThread() + " logService.log start, " + System.currentTimeMillis());
        logService.log("async method run!");
        System.out.println(Thread.currentThread() + " logService.log end, " + System.currentTimeMillis());
        // sleep 5 seconds for avoiding program exit.
        TimeUnit.SECONDS.sleep(5);
    }
}
