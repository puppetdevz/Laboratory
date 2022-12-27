package top.puppetdev.demo.demo05;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-12-27 下午 10:17
 */
@Component  // 使用 @Component 将这个类注册到 Spring 容器
@Aspect     // 使用 @Aspect 标注着是一个 AspectJ 来定义通知的配置类；
public class MyAspect {

    // 定义切入点，目前的配置，会拦截 test1 包及其子包中所有类的所有方法，
    // 而 CarService 和 UserService 刚好满足，所以会被拦截；
    @Pointcut("execution(* top.puppetdev.demo.demo05..*(..))")
    public void myPointcut() {
    }

    // 定义一个前置通知，这个通知会对 @3 定义的切入点起效；
    @Before("top.puppetdev.demo.demo05.MyAspect.myPointcut()")
    public void before(JoinPoint joinPoint) {
        System.out.println("我是前置通知，target：" + joinPoint.getTarget());
    }
}
