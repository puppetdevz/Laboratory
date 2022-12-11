package top.puppetdev.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author puppet
 * @since 2022/9/22 23:26
 */
@ToString
public class DiAutowireByType {
    private Service1 service1;
    private Service2 service2;
    
    public Service1 getService1() {
        return service1;
    }
    
    public void setService1(Service1 service1) {
        System.out.println("setService1->" + service1);
        this.service1 = service1;
    }
    
    public Service2 getService2() {
        return service2;
    }
    
    public void setService2(Service2 service2) {
        System.out.println("setService2->" + service2);
        this.service2 = service2;
    }
    
    @ToString
    @Setter
    @Getter
    public static class Service1 {
        private String desc;
    }
    
    @ToString
    @Setter
    @Getter
    public static class Service2 {
        private String desc;
    }
}
