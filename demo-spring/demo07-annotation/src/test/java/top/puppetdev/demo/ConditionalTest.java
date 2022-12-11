package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.anno04_conditional.MainBeanConfig;

/**
 * @author puppet
 * @since 2022-10-13 下午 12:09
 */
public class ConditionalTest {
    @Test
    public void test1() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainBeanConfig.class);
        System.out.println(context.getBean("name"));
    }
}
