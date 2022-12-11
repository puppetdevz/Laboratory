package top.puppetdev.demo.demo02;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @author puppet
 * @since 2022-11-28 下午 01:53
 */
public class CglibProxyForMultiInterfacesTest {
    interface Service1 {
        void m1();
    }

    interface Service2 {
        void m2();
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setInterfaces(new Class[]{Service1.class, Service2.class});
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                System.out.println("方法：" + method.getName());
                return null;
            }
        });

        Object proxy = enhancer.create();
        if (proxy instanceof Service1) {
            ((Service1) proxy).m1();
        }
        if (proxy instanceof Service2) {
            ((Service2) proxy).m2();
        }
        System.out.println(proxy.getClass());
        for (Class<?> anInterface : proxy.getClass().getInterfaces()) {
            System.out.println(anInterface);
        }
    }
}
