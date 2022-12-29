package top.puppetdev.demo.demo01;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-12-29 上午 09:49
 */
@Component
public class PushJob {

    // 每秒执行一次
    @Scheduled(fixedRate = 1000)
    public void push() {
        System.out.println("simulate message push: " + System.currentTimeMillis());
    }
}
