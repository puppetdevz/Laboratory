package top.puppetdev.demo.demo03;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.Nullable;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import top.puppetdev.demo.demo01.LogService;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022-12-28 下午 11:38
 */
@EnableAsync
public class MainConfig {
    @Bean
    public LogService logService() {
        return new LogService();
    }

    /**
     * 定义一个 AsyncConfigurer 类型的 bean，实现 getAsyncExecutor 方法，返回自定义的线程池
     * @param executor
     * @return
     */
    @Bean
    public AsyncConfigurer asyncConfigurer(@Qualifier("logExecutors") Executor executor) {
        return new AsyncConfigurer() {
            @Nullable
            @Override
            public Executor getAsyncExecutor() {
                return executor;
            }
        };
    }

    /**
     * 定义一个线程池，用来异步处理日志方法调用
     * @return
     */
    @Bean
    public Executor logExecutors() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(100);
        // 线程名称前缀
        executor.setThreadNamePrefix("log-thread-");  // @1
        return executor;
    }

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        LogService logService = context.getBean(LogService.class);
        System.out.println(Thread.currentThread() + " logService.log start, " + System.currentTimeMillis());
        logService.log("异步执行方法!");
        System.out.println(Thread.currentThread() + " logService.log end, " + System.currentTimeMillis());

        // 休眠一下，防止程序退出
        TimeUnit.SECONDS.sleep(3);
    }
}
