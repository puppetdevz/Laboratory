package top.puppetdev.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @author puppet
 * @since 2022/9/14 00:15
 */
public class ScopeTest {
    
    ApplicationContext context;
    
    @Before
    public void before() {
        System.out.println("Spring容器准备启动.....");
        // 1. bean 配置文件位置
        String beanXml = "classpath:beans.xml";
        // 2. 创建 ClassPathXmlApplicationContext 容器，给容器指定需要加载的 bean 配置文件
        this.context = new ClassPathXmlApplicationContext(beanXml);
        System.out.println("Spring容器启动完毕！");
    }
    
    @Test
    public void testSingletonBean() {
        System.out.println("---------单例bean，每次获取的bean实例都一样---------");
        System.out.println(context.getBean("singletonBean"));
        System.out.println(context.getBean("singletonBean"));
        System.out.println(context.getBean("singletonBean"));
    }
    
    @Test
    public void testPrototypeBean() {
        System.out.println("--------- 多例 bean，每次获取的 bean 实例都不一样 ---------");
        System.out.println(context.getBean("prototypeBean"));
        System.out.println(context.getBean("prototypeBean"));
        System.out.println(context.getBean("prototypeBean"));
    }
    
    @Test
    public void testCustomThreadScope() throws InterruptedException {
        String beanXml = "classpath:beans.xml";
        // 手动创建一个空的容器
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext();
        // 指定配置文件，并刷新容器
        context.setConfigLocation(beanXml);
        context.refresh();
        // 向容器注册自定义的 Scope
        context.getBeanFactory().registerScope(CustomThreadScope.THREAD_SCOPE, new CustomThreadScope());
        // 使用容器获取 bean
        // 创建 2 个线程，然后在每个线程中获取同样的 bean 2 次，然后输出
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread() + ": " + context.getBean("customThreadBean"));
                System.out.println(Thread.currentThread() + ": " + context.getBean("customThreadBean"));
            }).start();
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
