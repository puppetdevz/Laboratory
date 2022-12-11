package top.puppetdev.demo.anno02_configuration;

import org.springframework.context.annotation.Bean;

/**
 * @author puppet
 * @since 2022/9/30 13:54
 */
public class BeansConfigB {
    @Bean
    public ServiceA serviceA() {
        System.out.println("调用 serviceA 方法");  // @0
        return new ServiceA();
    }

    @Bean
    ServiceB serviceB1() {
        System.out.println("调用 serviceB1 方法");
        ServiceA serviceA = this.serviceA();
        return new ServiceB(serviceA);
    }

    @Bean
    ServiceB serviceB2() {
        System.out.println("调用 serviceB2 方法");
        ServiceA serviceA = this.serviceA();
        return new ServiceB(serviceA);
    }
}
