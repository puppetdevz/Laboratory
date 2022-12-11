package top.puppetdev.demo.demo02;

import org.springframework.aop.ThrowsAdvice;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author puppet
 * @since 2022-12-12 上午 01:35
 */
public class SendMsgThrowsAdvice implements ThrowsAdvice {
    /**
     * 方法名必须为 afterThrowing
     * @param method
     * @param args
     * @param target
     * @param e
     */
    public void afterThrowing(Method method, Object[] args, Object target, RuntimeException e) {
        // 监控到异常后发送消息通知开发者
        System.out.println("异常警报：");
        System.out.printf("method:[%s]，args:[%s]%n", method.toGenericString(), Arrays.stream(args).collect(Collectors.toList()));
        System.out.println(e.getMessage());
        System.out.println("请尽快修复bug！");
    }
}
