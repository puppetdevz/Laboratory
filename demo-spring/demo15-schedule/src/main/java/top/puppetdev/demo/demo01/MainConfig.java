package top.puppetdev.demo.demo01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-12-29 上午 09:52
 */
@ComponentScan
@EnableScheduling // 在 Spring 容器中启用定时任务功能
public class MainConfig {
    @Bean
    public ScheduledExecutorService scheduledExecutorService() {
        return Executors.newScheduledThreadPool(20);
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        TimeUnit.SECONDS.sleep(10000);
    }
}
