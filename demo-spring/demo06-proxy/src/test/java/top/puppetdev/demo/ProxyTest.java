package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.cglib.proxy.*;
import top.puppetdev.demo.demo01.*;
import top.puppetdev.demo.demo02.Service2;
import top.puppetdev.demo.demo02.Service3;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author puppet
 * @since 2022/9/27 14:59
 */
public class ProxyTest {

    @Test
    public void testJdkDynamicProxy() {
        // 创建代理类的处理器
        InvocationHandler handler = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("我是InvocationHandler，被调用的方法是：" + method.getName());
                return null;
            }
        };

        // 创建代理实例
        IService proxyService = (IService) Proxy.newProxyInstance(IService.class.getClassLoader(), new Class[]{IService.class}, handler);
        // 调用代理方法
        proxyService.m1();
        proxyService.m2();
        proxyService.m3();
    }

    @Test
    public void testCostTimeProxy() {
        IService serviceAProxy = CostTimeInvocationHandler.createProxy(new ServiceA(), IService.class);
        IService serviceBProxy = CostTimeInvocationHandler.createProxy(new ServiceB(), IService.class);
        serviceAProxy.m1();
        serviceAProxy.m2();
        serviceAProxy.m3();

        serviceBProxy.m1();
        serviceBProxy.m2();
        serviceBProxy.m3();
    }

    @Test
    public void testCglib() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service1.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("调用方法: " + method);
                Object result = methodProxy.invokeSuper(o, objects);
                return result;
            }
        });
        Service1 service1 = (Service1) enhancer.create();
        service1.m1();
        service1.m2();
    }

    @Test
    public void testCglibMethodInterceptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service2.class);
        enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> {
            System.out.println("被调用的方法：" + method);
            Object result = methodProxy.invokeSuper(o, objects);
            return result;
        });
        Service2 service2 = (Service2) enhancer.create();
        service2.m1();
        service2.m2();
    }

    @Test
    public void testCglibCallbackFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);
        Callback[] callbacks = {
                (MethodInterceptor) (o, method, objects, methodProxy) -> {
                    long start = System.nanoTime();
                    Object result = methodProxy.invokeSuper(o, objects);
                    long end = System.nanoTime();
                    System.out.println(method + ", 耗时（纳秒）：" + (end - start));
                    return result;
                },
                (FixedValue) () -> "puppet"
        };

        enhancer.setCallbacks(callbacks);
        enhancer.setCallbackFilter(method -> method.getName().startsWith("insert") ? 0 : 1);
        Service3 service3 = (Service3) enhancer.create();
        service3.insert1();
        service3.insert2();
        System.out.println(service3.get1());
        System.out.println(service3.get2());
    }

    @Test
    public void testCglibCallbackHelperAndFilter() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service3.class);

        Callback costTimeCallback = (MethodInterceptor) (o, method, objects, methodProxy) -> {
            long start = System.nanoTime();
            Object result = methodProxy.invokeSuper(o, objects);
            long end = System.nanoTime();
            System.out.println(method + ", 耗时（纳秒）：" + (end - start));
            return result;
        };

        Callback fixedValueCallback = (FixedValue) () -> "puppet";

        CallbackHelper callbackHelper = new CallbackHelper(Service3.class, null) {
            @Override
            protected Object getCallback(Method method) {
                return method.getName().startsWith("insert") ? costTimeCallback : fixedValueCallback;
            }
        };

        enhancer.setCallbacks(callbackHelper.getCallbacks());
        enhancer.setCallbackFilter(callbackHelper);
        Service3 o = (Service3) enhancer.create();
        System.out.println(o.get1());
        System.out.println(o.get2());
        o.insert1();
        o.insert2();
    }


}
