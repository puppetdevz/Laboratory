package top.puppetdev.demo.demo02;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-11-25 下午 11:14
 */
@Component
public class BeanDefinitionRegistryPostProcessor2 implements BeanDefinitionRegistryPostProcessor, Ordered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.printf("BeanDefinitionRegistryPostProcessor2{order=%d}，注册address bean, %n", this.getOrder());
        // 定义一个bean
        AbstractBeanDefinition addressBdf = BeanDefinitionBuilder.
                genericBeanDefinition(String.class).
                addConstructorArgValue("广州市").
                getBeanDefinition();
        // 将定义的bean注册到容器
        registry.registerBeanDefinition("address", addressBdf);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return 1;
    }
}
