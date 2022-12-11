package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.demo01.MainConfig1;
import top.puppetdev.demo.demo02.MainConfig2;
import top.puppetdev.demo.demo03.LessonModel;
import top.puppetdev.demo.demo03.MainConfig3;

/**
 * @author puppet
 * @since 2022-11-25 下午 08:48
 */
public class BeanFactoryPostProcessorTest {
    @Test
    public void testBeanDefinitionRegistryPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig1.class);
        context.refresh();
        System.out.println(context.getBean("username"));
    }

    @Test
    public void testOrderedBeanDefinitionRegistryPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig2.class);
        context.refresh();
        context.getBeansOfType(String.class).forEach((beanName, bean) -> {
            System.out.printf("%s -> %s%n", beanName, bean);
        });
    }

    @Test
    public void testBeanFactoryPostProcessor() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig3.class);
        context.refresh();
        System.out.println(context.getBean(LessonModel.class));
    }
}
