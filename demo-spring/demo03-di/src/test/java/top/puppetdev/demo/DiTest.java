package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * @author puppet
 * @since 2022/9/19 16:03
 */
public class DiTest {
    
    @Test
    public void testDiByConstructorParamIndex() {
        ApplicationContext context = IocUtils.getContext();
        System.out.println(context.getBean("beanShowDiByConstructorParamIndex"));
    }
    
    @Test
    public void testDiByConstructorParamType() {
        ApplicationContext context = IocUtils.getContext();
        System.out.println(context.getBean("beanShowDiByConstructorParamType"));
    }
    
    @Test
    public void testDiByConstructorParamName() {
        ApplicationContext context = IocUtils.getContext();
        System.out.println(context.getBean("beanShowDiByConstructorParamName"));
    }
    
    @Test
    public void testDiBySetter() {
        ApplicationContext context = IocUtils.getContext();
        System.out.println(context.getBean("beanShowDiBySetter"));
    }
    
    @Test
    public void testDiOtherBeans() {
        ApplicationContext context = IocUtils.getContext();
        System.out.println(context.getBean("beanShowDiOtherBeans"));
    }
}
