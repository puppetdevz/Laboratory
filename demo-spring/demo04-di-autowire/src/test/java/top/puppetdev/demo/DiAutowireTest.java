package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.context.ApplicationContext;

import java.util.Collection;
import java.util.List;

/**
 * @author puppet
 * @since 2022/9/22 23:39
 */
public class DiAutowireTest {
    
    @Test
    public void testIsAssignableFrom() {
        System.out.println(Object.class.isAssignableFrom(Integer.class));  //true
        System.out.println(Object.class.isAssignableFrom(int.class)); //false
        System.out.println(Object.class.isAssignableFrom(List.class)); //true
        System.out.println(Collection.class.isAssignableFrom(List.class)); //true
        System.out.println(List.class.isAssignableFrom(Collection.class)); //false
    }
    
    @Test
    public void testDiAutowireByName() {
        String beanXml = "classpath:diAutowireByName.xml";
        ApplicationContext context = IocUtils.getContext(beanXml);
        System.out.println(context.getBean("diAutowireByName1"));
    }
    
    @Test
    public void testDiAutowireByType() {
        String beanXml = "classpath:diAutowireByType.xml";
        ApplicationContext context = IocUtils.getContext(beanXml);
        System.out.println(context.getBean("diAutowireByType1"));
    }
    
    @Test
    public void testDiAutowireByTypeExtend() {
        String beanXml = "classpath:diAutowireByTypeExtend.xml";
        ApplicationContext context = IocUtils.getContext(beanXml);
        DiAutowireByTypeExtend bean = context.getBean(DiAutowireByTypeExtend.class);
        System.out.println("serviceList：" + bean.getServiceList());
        System.out.println("baseServiceList：" + bean.getBaseServiceList());
        System.out.println("serviceMap：" + bean.getServiceMap());
        System.out.println("baseServiceMap：" + bean.getBaseServiceMap());
    }
}
