package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import top.puppetdev.demo.demo04.MySmartInstantiationAwareBeanPostProcessor;
import top.puppetdev.demo.demo04.Person;

/**
 * 通过 {@link org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor#determineCandidateConstructors(Class, String)} 来确定使用哪个构造器来创建 bean 实例
 * @author puppet
 * @since 2022-11-13 上午 03:43
 */
public class SmartInstantiationAwareBeanPostProcessorTest {
    @Test
    public void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 创建一个 SmartInstantiationAwareBeanPostProcessor, 将其添加到容器中
        factory.addBeanPostProcessor(new MySmartInstantiationAwareBeanPostProcessor());

        factory.registerBeanDefinition("name", BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("木偶")
                .getBeanDefinition());

        factory.registerBeanDefinition("age", BeanDefinitionBuilder.genericBeanDefinition(Integer.class)
                .addConstructorArgValue(18)
                .getBeanDefinition());

        factory.registerBeanDefinition("person", BeanDefinitionBuilder.genericBeanDefinition(Person.class)
                .getBeanDefinition());

        Person person = factory.getBean("person", Person.class);
        System.out.println(person);
    }
}
