package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * BeanDefinition 合并
 * @author puppet
 * @since 2022-11-12 上午 01:28
 */
public class MergedBeanDefinitionTest {
    @Test
    public void test1() {
        // 创建 bean 容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 创建一个 beans.xml 解析器
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        // 解析 beans.xml，将解析过程中产生的 BeanDefinition 注册到容器中
        xmlBeanDefinitionReader.loadBeanDefinitions("beans2.xml");
        // 遍历容器中注册的所有 bean 信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            // 通过 bean 名称获取原始的注册的 BeanDefinition 信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            // 获取合并之后的 BeanDefinition 信息
            BeanDefinition mergedBeanDefinition = factory.getMergedBeanDefinition(beanName);

            System.out.println(beanName);
            System.out.println("解析xml过程中注册的beanDefinition：" + beanDefinition);
            System.out.println("beanDefinition中的属性信息" + beanDefinition.getPropertyValues());
            System.out.println("合并之后得到的mergedBeanDefinition：" + mergedBeanDefinition);
            System.out.println("mergedBeanDefinition中的属性信息" + mergedBeanDefinition.getPropertyValues());
            System.out.println("---------------------------");
        }
    }
}
