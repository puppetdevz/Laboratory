package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.anno03_import.MainBeansConfig01;
import top.puppetdev.demo.anno03_import.MainBeansConfig02;
import top.puppetdev.demo.anno03_import.MainBeansConfig03;
import top.puppetdev.demo.anno03_import.MainBeansConfig04;
import top.puppetdev.demo.anno03_import.demo_cost_time.MainBeansConfig;
import top.puppetdev.demo.anno03_import.demo_cost_time.Service1;
import top.puppetdev.demo.anno03_import.demo_cost_time.Service2;

/**
 * @author puppet
 * @since 2022-10-07 下午 08:12
 */
public class ImportAnnoTest {

    @Test
    public void testImportRegularComponentClasses() {
        // 1. 通过 AnnotationConfigApplicationContext 创建 Spring 容器，参数为 @Import 标注的类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainBeansConfig01.class);
        // 2. 输出容器中定义的所有 bean 信息
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(String.format("%s -> %s", beanName, context.getBean(beanName)));
        }
    }

    @Test
    public void testImportComponentClasses() {
        // 1. 通过 AnnotationConfigApplicationContext 创建 Spring 容器，参数为 @Import 标注的类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainBeansConfig02.class);
        // 2. 输出容器中定义的所有 bean 信息
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(String.format("%s -> %s", beanName, context.getBean(beanName)));
        }
    }

    @Test
    public void test4() {
        // 1. 通过 AnnotationConfigApplicationContext 创建 Spring 容器，参数为 @Import 标注的类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainBeansConfig03.class);
        // 2. 输出容器中定义的所有 bean 信息
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(String.format("%s -> %s", beanName, context.getBean(beanName)));
        }
    }

    @Test
    public void test5() {
        // 1. 通过 AnnotationConfigApplicationContext 创建 Spring 容器，参数为 @Import 标注的类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainBeansConfig04.class);
        // 2. 输出容器中定义的所有 bean 信息
        for (String beanName : context.getBeanDefinitionNames()) {
            System.out.println(String.format("%s -> %s", beanName, context.getBean(beanName)));
        }
    }

    @Test
    public void test6() {
        // 1. 通过 AnnotationConfigApplicationContext 创建 Spring 容器，参数为 @Import 标注的类
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainBeansConfig.class);
        Service1 service1 = context.getBean(Service1.class);
        Service2 service2 = context.getBean(Service2.class);
        service1.m1();
        service2.m1();
    }
}
