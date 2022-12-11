package top.puppetdev.demo.module2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-11-14 下午 01:26
 */
@SuppressWarnings("AlibabaCommentsMustBeJavadocFormat")
@Component
public class Service3 {
    // 使用模块 2 中的 Service1
    @Autowired
    private top.puppetdev.demo.module2.Service1 service1;

    // 使用模块 1 中的 Service2
    @Autowired
    private top.puppetdev.demo.module1.Service2 service2;

    public String m1() {
        return this.service2.m1();
    }

    public String m2() {
        return this.service1.m2();
    }
}
