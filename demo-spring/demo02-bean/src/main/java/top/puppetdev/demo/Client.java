package top.puppetdev.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author puppet
 * @since 2022/9/11 02:58
 */
public class Client {
    public static void main(String[] args) {
        // 1. beans.xml 的类路径
        String beanXmlPath = "classpath:beans.xml";
        // 2. 创建 ClassPathXmlApplicationContext 容器
        ApplicationContext context = new ClassPathXmlApplicationContext(beanXmlPath);
        
        System.out.println("All beans of SC are as follows: ");
        // 获取容器中所有定义的 bean 的名称
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(String.format("%s : %s", beanDefinitionName, context.getBean(beanDefinitionName)));
        }
        
        System.out.println("--------------------------");
        //多次获取createByFactoryBean看看是否是同一个对象
        System.out.println("beanCreatedByFactoryBean:" + context.getBean("beanCreatedByFactoryBean"));
        System.out.println("beanCreatedByFactoryBean:" + context.getBean("beanCreatedByFactoryBean"));
    }
}
