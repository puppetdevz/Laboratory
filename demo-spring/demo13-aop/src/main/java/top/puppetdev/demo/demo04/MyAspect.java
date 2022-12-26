package top.puppetdev.demo.demo04;

import netscape.javascript.JSObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author puppet
 * @since 2022/12/19 22:09
 */
@Aspect  // @1：用 @Aspect 注解进行标注
public class MyAspect {
    // @2：定义一个切入点，可以匹配 MyService 中的所有方法
    @Pointcut("execution(* top.puppetdev.demo.demo04.MyService.*(..))")
    public void myPointcut() {

    }

    // @3：定义一个前置通知，这个通知对上面定义的切入点有效
    @Before(value = "myPointcut()")
    public void before(JoinPoint joinPoint) {
        // 输出连接点的信息
        System.out.println("前置通知：" + joinPoint);
    }

    // @4：定义一个异常通知，这个通知对上面定义的切入点有效
    @AfterThrowing(value = "myPointcut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        // 发生异常后输出异常信息
        System.out.printf("%s exception occurred: %s%n", joinPoint, e.getMessage());
    }
}
