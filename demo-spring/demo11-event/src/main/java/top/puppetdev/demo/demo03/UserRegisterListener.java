package top.puppetdev.demo.demo03;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-11-17 下午 02:44
 */
@Component
public class UserRegisterListener {
    @EventListener
    public void sendMail(UserRegisterEvent event) {
        System.out.printf("给用户【%s】发送注册成功邮件！", event.getUsername());
    }

    @EventListener
    public void sendCoupon(UserRegisterEvent event) {
        System.out.printf("给用户【%s】发送优惠券!", event.getUsername());
    }
}
