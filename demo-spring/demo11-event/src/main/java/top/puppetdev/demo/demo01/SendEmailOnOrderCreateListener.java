package top.puppetdev.demo.demo01;

import org.springframework.context.ApplicationListener;

/**
 * 订单创建成功后给用户发送邮件
 * @author puppet
 * @since 2022-11-17 下午 01:26
 */
public class SendEmailOnOrderCreateListener implements ApplicationListener<OrderCreateEvent> {
    @Override
    public void onApplicationEvent(OrderCreateEvent event) {
        System.out.printf("订单【%d】创建成功，给下单人发送邮件通知！", event.getOrderId());
    }
}
