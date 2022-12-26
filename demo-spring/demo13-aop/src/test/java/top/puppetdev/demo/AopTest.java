package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.demo01.UserService;
import top.puppetdev.demo.demo02.FundsService;
import top.puppetdev.demo.demo02.IService;
import top.puppetdev.demo.demo02.MyService;
import top.puppetdev.demo.demo02.SendMsgThrowsAdvice;
import top.puppetdev.demo.demo03.MainConfig;
import top.puppetdev.demo.demo04.MyAspect;

import java.lang.reflect.Method;

/**
 * @author puppet
 * @since 2022-11-29 下午 06:34
 */
public class AopTest {
    @Test
    public void testSimpleUsage() {
        MethodBeforeAdvice advice = (method, args, target) -> System.out.println("Hello, " + args[0]);
        Pointcut pointcut = new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return UserService.class::isAssignableFrom;
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        return "work".equals(method.getName());
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return false;
                    }
                };
            }
        };
        DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(pointcut, advice);
        ProxyFactory proxyFactory = new ProxyFactory();
        UserService target = new UserService();
        proxyFactory.setTarget(target);
        proxyFactory.addAdvisor(advisor);
        UserService proxy = (UserService) proxyFactory.getProxy();
        proxy.work("木偶");
    }

    @Test
    public void testMethodBeforeAdvice() {
        // 代理工厂
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        // 添加一个方法前置通知，当用户名不是“木偶”时，抛出非法访问异常
        proxyFactory.addAdvice(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                String username = String.valueOf(args[0]);
                if (!"木偶".equals(username)) {
                    throw new RuntimeException(String.format("[%s]非法访问", username));
                }
            }
        });

        // 通过代理工厂创建代理
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        proxy.recharge("木偶", 100);
        proxy.recharge("嘿嘿嘿", 1000);
    }

    @Test
    public void testAfterThrowing() {
        // 代理工厂
        ProxyFactory proxyFactory = new ProxyFactory(new FundsService());
        // 添加一个异常通知，发现异常之后发送消息给开发者尽快修复 bug
        proxyFactory.addAdvice(new SendMsgThrowsAdvice());
        // 通过代理工厂创建代理
        FundsService proxy = (FundsService) proxyFactory.getProxy();
        proxy.withdrawal("木偶", 2000);
    }

    @Test
    public void testGetProxyObjectInfo() {
        ProxyFactory proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(new FundsService());
        proxyFactory.addAdvisor(new DefaultPointcutAdvisor(new MethodBeforeAdvice() {
            @Override
            public void before(Method method, Object[] args, Object target) throws Throwable {
                System.out.println(method);
            }
        }));
        // 创建代理对象
        Object proxy = proxyFactory.getProxy();
        System.out.println("the type of proxy object is [" + proxy.getClass() + "]");
        System.out.println("the parent class of proxy object is [" + proxy.getClass().getSuperclass() + "]");
        System.out.println("the interfaces implemented by proxy object are: ");
        for (Class<?> anInterface : proxy.getClass().getInterfaces()) {
            System.out.println("    " + anInterface);
        }
    }

    @Test
    public void testProxiedByJdkDynamicProxy() {
        IService myService = new MyService();
        ProxyFactory proxyFactory = new ProxyFactory();
        // 设置被代理的对象
        proxyFactory.setTarget(myService);
        // 设置需要代理的接口
        proxyFactory.setInterfaces(IService.class);
        // proxyFactory.setProxyTargetClass(true);
        proxyFactory.addAdvice((MethodBeforeAdvice) (method, args, target) -> System.out.println(method));

        // 创建代理对象
        IService proxy = ((IService) proxyFactory.getProxy());
        System.out.println("the type of proxy object is [" + proxy.getClass() + "]");
        System.out.println("the parent class of proxy object is [" + proxy.getClass().getSuperclass() + "]");
        System.out.println("the interfaces implemented by proxy object are: ");
        for (Class<?> anInterface : proxy.getClass().getInterfaces()) {
            System.out.println("    " + anInterface);
        }

        proxy.say("aop");
    }

    @Test
    public void testProxyFactoryBeanSimpleUsage() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        // 获取代理对象，代理对象 bean 的名称为注册 ProxyFactoryBean 的名称，即：myServiceProxy
        top.puppetdev.demo.demo03.MyService bean = context.getBean("myServiceProxy", top.puppetdev.demo.demo03.MyService.class);
        System.out.println("----------------------");
        // 调用代理的方法
        bean.m1();
        System.out.println("----------------------");
        // 调用代理的方法
        bean.m2();
    }

    @Test
    public void testAspectJProxyFactorySimpleUsage() {
        try {
            // 创建目标对象 myService
            top.puppetdev.demo.demo04.MyService myService = new top.puppetdev.demo.demo04.MyService();
            // 创建 AspectJProxyFactory 对象
            AspectJProxyFactory proxyFactory = new AspectJProxyFactory();
            // 设置被代理的目标对象
            proxyFactory.setTarget(myService);
            // 设置标注了 @Aspect 注解的类
            proxyFactory.addAspect(MyAspect.class);
            // 生成代理对象
            top.puppetdev.demo.demo04.MyService proxy = proxyFactory.getProxy();
            proxy.m1();
            proxy.m2();
        } catch (Exception e) {

        }
    }
}
