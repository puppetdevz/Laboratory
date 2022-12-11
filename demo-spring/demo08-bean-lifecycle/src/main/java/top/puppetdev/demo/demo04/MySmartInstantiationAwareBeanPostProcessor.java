package top.puppetdev.demo.demo04;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

/**
 * @author puppet
 * @since 2022-11-13 上午 03:16
 */
public class MySmartInstantiationAwareBeanPostProcessor implements SmartInstantiationAwareBeanPostProcessor {
    @Override
    public Constructor<?>[] determineCandidateConstructors(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println(beanClass);
        System.out.println("调用MySmartInstantiationAwareBeanPostProcessor.determineCandidateConstructors方法");
        Constructor<?>[] declaredConstructors = beanClass.getDeclaredConstructors();
        // 获取被 @MyAutowired 注解修饰的构造器列表
        List<Constructor<?>> collect = Arrays.stream(declaredConstructors)
                .filter(constructor -> constructor.isAnnotationPresent(MyAutowired.class)).toList();
        return collect.size() != 0 ? collect.toArray(new Constructor[0]) : null;
    }
}
