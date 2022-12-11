package top.puppetdev.demo.anno03_import.demo_cost_time;

import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-10-09 下午 08:52
 */
@Component
public class Service2 {
    public void m1() {
        System.out.println(this.getClass() + ".m1()");
    }
}
