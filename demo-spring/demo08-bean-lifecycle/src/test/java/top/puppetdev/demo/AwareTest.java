package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import top.puppetdev.demo.demo06.AwareBean;

/**
 * @author puppet
 * @since 2022-11-13 下午 10:27
 */
public class AwareTest {
    @Test
    public void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 注册 bean 对象
        factory.registerBeanDefinition("awareBean", BeanDefinitionBuilder.genericBeanDefinition(AwareBean.class).getBeanDefinition());
        // 调用 getBean 方法获取 bean，由此触发 bean 的初始化
        factory.getBean("awareBean");
    }
}
