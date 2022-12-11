package top.puppetdev.demo.demo02;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author puppet
 * @since 2022-11-28 下午 03:57
 */
public class CglibProxyForClassAndInterfacesTest {
    interface IService1 {
        void m1();
    }

    interface IService2 {
        void m2();
    }

    public static class MyService implements IService1, IService2 {
        @Override
        public void m1() {
            System.out.println("m1");
        }

        @Override
        public void m2() {
            System.out.println("m2");
        }
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyService.class);
        enhancer.setInterfaces(new Class[]{IService1.class, IService2.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                long start = System.nanoTime();
                Object result = methodProxy.invokeSuper(o, objects);
                long end = System.nanoTime();
                System.out.println(method + "，耗时(纳秒):" + (start - end));
                return result;
            }
        });

        Object proxy = enhancer.create();
        System.out.println("proxy instanceof Service: " + (proxy instanceof MyService));
        if (proxy instanceof MyService) {
            MyService service = (MyService) proxy;
            service.m1();
            service.m2();
        }
        System.out.println(proxy.getClass());
        System.out.println("the parent class of proxy is " + proxy.getClass().getSuperclass());
        for (Class<?> anInterface : proxy.getClass().getInterfaces()) {
            System.out.println(anInterface);
        }
    }
}
