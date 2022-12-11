package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.anno02_configuration.BeansConfig;
import top.puppetdev.demo.anno02_configuration.BeansConfigA;
import top.puppetdev.demo.anno02_configuration.BeansConfigB;

import java.util.Arrays;

/**
 * @author puppet
 * @since 2022/9/30 01:49
 */
public class ConfigurationTest {
    @Test
    public void test1() {
        // 通过 AnnotationConfigApplicationContext 来加载配置类 BeansConfig，
        // 会将配置类中所有的 bean 注册到 Spring 容器中
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            // 获取别名
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.println(String.format("bean名称: %s, 别名: %s, bean对象: %s",
                    beanDefinitionName,
                    Arrays.asList(aliases),
                    context.getBean(beanDefinitionName)));
        }
    }

    @Test
    public void test2() {
        // 通过 AnnotationConfigApplicationContext 来加载配置类 BeansConfig，
        // 会将配置类中所有的 bean 注册到 Spring 容器中
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfigA.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            // 获取别名
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.println(String.format("bean名称: %s, 别名: %s, bean对象: %s",
                    beanDefinitionName,
                    Arrays.asList(aliases),
                    context.getBean(beanDefinitionName)));
        }
    }

    @Test
    public void test3() {
        // 通过 AnnotationConfigApplicationContext 来加载配置类 BeansConfig，
        // 会将配置类中所有的 bean 注册到 Spring 容器中
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfigB.class);
        for (String beanDefinitionName : context.getBeanDefinitionNames()) {
            // 获取别名
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.println(String.format("bean名称: %s, 别名: %s, bean对象: %s",
                    beanDefinitionName,
                    Arrays.asList(aliases),
                    context.getBean(beanDefinitionName)));
        }
    }

    @Test
    public void test4() {
        // 通过 AnnotationConfigApplicationContext 来加载配置类 BeansConfig，
        // 会将配置类中所有的 bean 注册到 Spring 容器中
        ApplicationContext context = new AnnotationConfigApplicationContext(BeansConfigB.class);

        System.out.println(context.getBean("serviceA"));
        System.out.println(context.getBean("serviceA"));
    }
}
