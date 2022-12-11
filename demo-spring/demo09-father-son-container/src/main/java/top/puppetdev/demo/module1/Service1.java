package top.puppetdev.demo.module1;

import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-11-14 下午 01:21
 */
@Component
public class Service1 {
    public String m1() {
        return "我是module1中的Service1中的m1方法";
    }
}
