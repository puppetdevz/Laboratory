package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.module1.Module1BeanConfig;
import top.puppetdev.demo.module2.Module2BeanConfig;
import top.puppetdev.demo.module2.Service3;

import java.util.List;
import java.util.Map;

/**
 * @author puppet
 * @since 2022-11-14 下午 01:28
 */
public class ParentFactoryTest {
    @Test
    public void test1() {
        //定义容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        //注册bean
        context.register(Module1BeanConfig.class, Module2BeanConfig.class);
        //启动容器
        context.refresh();
    }

    @Test
    public void test2() {
        // 创建父容器
        AnnotationConfigApplicationContext parentContext = new AnnotationConfigApplicationContext();
        // 向父容器注册 Module1BeanConfig 配置类
        parentContext.register(Module1BeanConfig.class);
        // 启动父容器
        parentContext.refresh();

        // 创建子容器
        AnnotationConfigApplicationContext childContext = new AnnotationConfigApplicationContext();
        // 向子容器注册 Module2BeanConfig 配置类
        childContext.register(Module2BeanConfig.class);
        // 给子容器设置父容器
        childContext.setParent(parentContext);
        // 启动子容器
        childContext.refresh();

        // 获取 service3，并调用方法输出
        Service3 service3 = childContext.getBean(Service3.class);
        System.out.println(service3.m1());
        System.out.println(service3.m2());
    }

    @Test
    public void test3() {
        // 创建父容器 parentFactory
        DefaultListableBeanFactory parentFactory = new DefaultListableBeanFactory();
        // 向父容器 parentFactory 注册一个 bean[username->"木偶"]
        parentFactory.registerBeanDefinition("username", BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("木偶")
                .getBeanDefinition());

        // 创建一个子容器 childFactory
        DefaultListableBeanFactory childFactory = new DefaultListableBeanFactory();
        // 调用 setParentBeanFactory 指定父容器
        childFactory.setParentBeanFactory(parentFactory);
        //向子容器 parentFactory 注册一个 bean[address->"广州"]
        childFactory.registerBeanDefinition("address", BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("上海")
                .getBeanDefinition());

        System.out.println("获取 bean【username】：" + childFactory.getBean("username"));//@1
        System.out.println(List.of(childFactory.getBeanNamesForType(String.class))); //@2
    }

    @Test
    public void test4() {
        // 创建父容器 parentFactory
        DefaultListableBeanFactory parentFactory = new DefaultListableBeanFactory();
        // 向父容器 parentFactory 注册一个 bean[username->"木偶"]
        parentFactory.registerBeanDefinition("username", BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("木偶")
                .getBeanDefinition());

        // 创建一个子容器 childFactory
        DefaultListableBeanFactory childFactory = new DefaultListableBeanFactory();
        // 调用 setParentBeanFactory 指定父容器
        childFactory.setParentBeanFactory(parentFactory);
        //向子容器 parentFactory 注册一个 bean[address->"广州"]
        childFactory.registerBeanDefinition("address", BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("上海")
                .getBeanDefinition());

        // 层次查找所有符合类型的 bean 名称
        String[] beanNamesForTypeIncludingAncestors = BeanFactoryUtils.beanNamesForTypeIncludingAncestors(childFactory, String.class);
        System.out.println(List.of(beanNamesForTypeIncludingAncestors));

        Map<String, String> beansOfTypeIncludingAncestors = BeanFactoryUtils.beansOfTypeIncludingAncestors(childFactory, String.class);
        System.out.println(List.of(beansOfTypeIncludingAncestors));
    }
}
