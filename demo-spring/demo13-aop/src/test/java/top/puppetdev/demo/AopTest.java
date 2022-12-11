package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import top.puppetdev.demo.demo01.UserService;

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
}
