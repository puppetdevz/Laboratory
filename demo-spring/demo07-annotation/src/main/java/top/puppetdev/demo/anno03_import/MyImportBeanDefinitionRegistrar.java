package top.puppetdev.demo.anno03_import;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author puppet
 * @since 2022-10-08 下午 10:54
 */
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // 定义一个 bean：Service1
        BeanDefinition service1BeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Service1.class).getBeanDefinition();
        // 注册 service1
        registry.registerBeanDefinition("service1", service1BeanDefinition);

        // 定义 bean：Service2，通过 addPropertyReference 注入 service1
        AbstractBeanDefinition service2BeanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Service2.class)
                .addPropertyReference("service1", "service1")
                .getBeanDefinition();
        // 注册 service2
        registry.registerBeanDefinition("service2", service2BeanDefinition);
    }
}
