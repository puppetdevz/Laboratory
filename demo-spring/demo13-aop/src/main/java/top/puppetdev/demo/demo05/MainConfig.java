package top.puppetdev.demo.demo05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author puppet
 * @since 2022-12-27 下午 10:21
 */
@ComponentScan
@EnableAspectJAutoProxy
public class MainConfig {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        UserService userService = context.getBean(UserService.class);
        userService.say();
        CarService carService = context.getBean(CarService.class);
        carService.say();
    }
}
