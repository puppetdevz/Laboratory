package top.puppetdev.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author puppet
 * @since 2022/9/19 15:57
 */
public class IocUtils {
    
    public static ApplicationContext getContext(String beanXml) {
        return new ClassPathXmlApplicationContext(beanXml);
    }
    
    public static ApplicationContext getContext() {
        String beanXml = "classpath:beans.xml";
        return new ClassPathXmlApplicationContext(beanXml);
    }
}
