package top.puppetdev.demo.anno02_configuration;

import lombok.ToString;

/**
 * @author puppet
 * @since 2022/9/30 13:53
 */
@ToString
public class ServiceB {
    private ServiceA serviceA;

    public ServiceB(ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
