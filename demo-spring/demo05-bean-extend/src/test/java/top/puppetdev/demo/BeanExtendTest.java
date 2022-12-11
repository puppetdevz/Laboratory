package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author puppet
 * @since 2022/9/25 17:30
 */
public class BeanExtendTest {
    @Test
    public void testLookupMethod() {
        String beanXml = "classpath:lookupMethod.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);
        
        System.out.println(context.getBean(ServiceA.class));
        System.out.println(context.getBean(ServiceA.class));
        
        System.out.println("serviceB 中的 serviceA");
        ServiceB serviceB = context.getBean(ServiceB.class);
        serviceB.say();
        serviceB.say();
    }
    
    @Test
    public void testReplacedMethod() {
        String beanXml = "classpath:lookupMethod.xml";
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(beanXml);
        
        System.out.println(context.getBean(ServiceA.class));
        System.out.println(context.getBean(ServiceA.class));
        
        System.out.println("serviceB 中的 serviceA");
        ServiceB serviceB = context.getBean(ServiceB.class);
        serviceB.say();
        serviceB.say();
    }
}
