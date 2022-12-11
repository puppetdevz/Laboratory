package top.puppetdev.demo.anno03_import.demo_cost_time;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * @author puppet
 * @since 2022-10-09 下午 09:02
 */
public class MethodCostTimeProxyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        final String name = "service";
        if (bean.getClass().getName().toLowerCase().contains(name)) {
            // 如果 bean 类名中包含了 service，则给该 bean 对象创建一个代理对象
            return CostTimeProxy.createProxy(bean);
        }
        // 否则直接返回
        return bean;
    }
}
