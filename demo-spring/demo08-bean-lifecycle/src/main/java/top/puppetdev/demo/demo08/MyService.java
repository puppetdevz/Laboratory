package top.puppetdev.demo.demo08;

import org.springframework.beans.factory.InitializingBean;

/**
 * @author puppet
 * @since 2022-11-14 上午 12:30
 */
public class MyService implements InitializingBean {
    public void init() {
        System.out.println("调用 init 方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("调用 afterPropertiesSet 方法");
    }
}
