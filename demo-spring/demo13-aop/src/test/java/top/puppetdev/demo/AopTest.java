package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import top.puppetdev.demo.demo01.UserService;
import top.puppetdev.demo.demo02.FundsService;

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
}
