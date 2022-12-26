package top.puppetdev.demo.demo03;

import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.invoke.VarHandle;
import java.lang.reflect.Method;

/**
 * @author puppet
 * @since 2022/12/17 14:20
 */
@Configuration
public class MainConfig {
    /**
     * 注册目标对象
     */
    @Bean
    public MyService myService() {
        return new MyService();
    }

    /**
     * register a method before advice.
     */
    @Bean
    public MethodBeforeAdvice beforeAdvice() {
        return (method, args, target) -> System.out.printf("ready to call method: [%s]%n", method);
    }

    /**
     * register a method post advice
     */
    @Bean
    public MethodInterceptor costTimeInterceptor() {
        return invocation -> {
            long start = System.nanoTime();
            Object result = invocation.proceed();
            System.out.printf("%s cost %d ns%n", invocation.getMethod(), System.nanoTime() - start);
            return result;
        };
    }

    /**
     * create proxy for myService bean
     */
    @Bean
    public ProxyFactoryBean myServiceProxy() {
        // 1. 创建 ProxyFactoryBean
        ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
        // 2. 设置目标对象的 bean 名称
        proxyFactoryBean.setTargetName("myService");
        // 3. 设置拦截器/通知列表（Bean名称）
        proxyFactoryBean.setInterceptorNames("beforeAdvice", "costTimeInterceptor");
        return proxyFactoryBean;
    }
}
