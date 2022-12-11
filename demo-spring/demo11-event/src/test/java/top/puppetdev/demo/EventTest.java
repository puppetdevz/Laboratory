package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import top.puppetdev.demo.demo01.OrderCreateEvent;
import top.puppetdev.demo.demo01.SendEmailOnOrderCreateListener;
import top.puppetdev.demo.demo03.MainConfig;
import top.puppetdev.demo.demo03.UserRegisterService;

/**
 * @author puppet
 * @since 2022-11-17 下午 01:27
 */
public class EventTest {
    @Test
    public void testSimpleEvent() {
        // 创建事件广播器，利用 Spring 现有的实现
        SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
        // 注册事件监听器
        multicaster.addApplicationListener(new SendEmailOnOrderCreateListener());
        // 广播“订单创建”事件
        multicaster.multicastEvent(new OrderCreateEvent(multicaster, 1L));
    }

    @Test
    public void testEventByApplicationEventPublisherAwareInterface() throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        //获取用户注册服务
        UserRegisterService userRegisterService = context.getBean(UserRegisterService.class);
        //模拟用户注册
        userRegisterService.registerUser("木偶");
    }
}
