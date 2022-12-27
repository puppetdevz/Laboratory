package top.puppetdev.demo.demo05;

import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-12-27 下午 10:16
 */
@Component
public class UserService {
    public void say() {
        System.out.println("我是UserService");
    }
}
