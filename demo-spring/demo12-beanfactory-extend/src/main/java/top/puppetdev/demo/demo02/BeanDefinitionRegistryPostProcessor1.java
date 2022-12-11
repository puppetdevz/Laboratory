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
 * @since 2022-11-25 下午 08:53
 */
@Component
public class BeanDefinitionRegistryPostProcessor1 implements BeanDefinitionRegistryPostProcessor, Ordered {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        System.out.printf("BeanDefinitionRegistryPostProcessor1{order=%d}，注册name bean,%n", this.getOrder());
        AbstractBeanDefinition nameBdf = BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("木偶")
                .getBeanDefinition();
        // 将定义的 bean 注册到容器
        registry.registerBeanDefinition("name", nameBdf);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public int getOrder() {
        return 2;
    }
}
