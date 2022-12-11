package top.puppetdev.demo.module2;

import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-11-14 下午 01:25
 */
@Component
public class Service1 {
    public String m2() {
        return "我是module2中的Service1中的m2方法";
    }
}
