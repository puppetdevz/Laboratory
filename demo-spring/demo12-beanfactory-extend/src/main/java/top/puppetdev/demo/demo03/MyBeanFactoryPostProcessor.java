package top.puppetdev.demo.demo03;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @author puppet
 * @since 2022-11-26 上午 01:03
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("准备修改lessonModel的bean定义信息");
        BeanDefinition lessonModel = beanFactory.getBeanDefinition("lessonModel");
        lessonModel.getPropertyValues().add("name", "Spring");
    }
}
