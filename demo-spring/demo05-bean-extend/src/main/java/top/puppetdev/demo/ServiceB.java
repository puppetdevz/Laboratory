package top.puppetdev.demo;

/**
 * @author puppet
 * @since 2022/9/25 17:29
 */
public class ServiceB {
    public void say() {
        ServiceA serviceA = this.getServiceA();
        System.out.println("this: " + this + ", serviceA: " + serviceA);
    }
    
    public ServiceA getServiceA() {
        return null;
    }
}
