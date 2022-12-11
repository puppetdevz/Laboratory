package top.puppetdev.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
import java.util.List;

/**
 * @author puppet
 * @since 2022/9/11 02:58
 */
public class Client {
    public static void main(String[] args) {
        // 1. bean.xml 的类路径
        String beanXmlPath = "classpath:bean.xml";
        // 2. 创建 ClassPathXmlApplicationContext 容器
        ApplicationContext context = new ClassPathXmlApplicationContext(beanXmlPath);
        
        // 3. 通过容器获取 bean 对象
        // HelloWorld helloWorld = (HelloWorld) context.getBean("helloWorld");
        // 或
        // HelloWorld helloWorld = context.getBean("helloWorld", HelloWorld.class);
    
        // helloWorld.say();
    
        List<String> beanNames = Arrays.asList("userA", "userB", "userC", "userD", "userE");
        for (String beanName : beanNames) {
            // 根据 bean 名称获取其别名，由于别名可能有多个，所以是一个数组
            String[] aliases = context.getAliases(beanName);
            System.out.println(String.format("the name of bean is 『%s』, its aliases are 『%s』", beanName, String.join("、", aliases)));
        }
    
        System.out.println("=====================>");
        System.out.println("All beans of SC are as follows: ");
        
        // 获取容器中所有定义的 bean 的名称
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // 根据 bean 名称获取其别名，由于别名可能有多个，所以是一个数组
            String[] aliases = context.getAliases(beanDefinitionName);
            System.out.println(String.format("the name of bean is 『%s』, its aliases are 『%s』", beanDefinitionName, String.join("、", aliases)));
            
        }
    }
}
