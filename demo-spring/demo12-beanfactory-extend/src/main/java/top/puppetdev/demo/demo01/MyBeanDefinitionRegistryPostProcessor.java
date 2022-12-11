package top.puppetdev.demo.demo01;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-11-25 下午 08:08
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        // 定义一个字符串类型的 bean
        AbstractBeanDefinition usernameBdf = BeanDefinitionBuilder.genericBeanDefinition(String.class)
                .addConstructorArgValue("木偶")
                .getBeanDefinition();
        // 将 usernameBdf 注册到 Spring 容器中
        registry.registerBeanDefinition("username", usernameBdf);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}
