package top.puppetdev.demo.anno03_import.demo_cost_time;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author puppet
 * @since 2022-10-09 下午 08:53
 */
public class CostTimeProxy implements MethodInterceptor {
    // 目标对象
    private Object target;

    public CostTimeProxy(Object target) {
        this.target = target;
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        long startTime = System.nanoTime();
        // 调用被代理对象（即 target）的方法，获取结果
        Object result = method.invoke(target, objects);
        long endTime = System.nanoTime();
        System.out.println(method + "，耗时(纳秒)：" + (endTime - startTime));
        return result;
    }

    /**
     * 创建任意类的代理对象
     * @param target
     * @param <T>
     * @return
     */
    public static <T> T createProxy(T target) {
        CostTimeProxy costTimeProxy = new CostTimeProxy(target);
        Enhancer enhancer = new Enhancer();
        enhancer.setCallback(costTimeProxy);
        enhancer.setSuperclass(target.getClass());
        return (T) enhancer.create();
    }
}
