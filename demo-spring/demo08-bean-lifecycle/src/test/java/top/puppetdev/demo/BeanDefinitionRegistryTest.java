package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.List;

/**
 * BeanDefinitionRegistry 案例
 * @author puppet
 * @since 2022-11-11 下午 08:11
 */
public class BeanDefinitionRegistryTest {
    @Test
    public void test1() {
        // 创建一个 bean 工厂，这个默认实现了 BeanDefinitionRegistry 接口，所以也是一个 bean 注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 定义一个bean
        GenericBeanDefinition nameBeanDefinition = new GenericBeanDefinition();
        nameBeanDefinition.setBeanClass(String.class);
        nameBeanDefinition.getConstructorArgumentValues().addIndexedArgumentValue(0, "木偶");

        // 将bean注册到容器中
        factory.registerBeanDefinition("name", nameBeanDefinition);

        // 通过 bean 名称获取 BeanDefinition
        System.out.println(factory.getBeanDefinition("name"));
        // 通过 bean 名称判断是否被注册过
        System.out.println(factory.containsBeanDefinition("name"));
        // 获取所有注册的 bean 的名称
        System.out.println(List.of(factory.getBeanDefinitionNames()));
        // 获取已注册的 BeanDefinition 的数量
        System.out.println(factory.getBeanDefinitionCount());
        // 判断指定的 name 是否使用过
        System.out.println(factory.isBeanNameInUse("name"));

        // 别名相关方法
        // 为name注册2个别名
        factory.registerAlias("name", "alias-name-1");
        factory.registerAlias("name", "alias-name-2");

        // 判断 alias-name-1 是否已被作为别名使用
        System.out.println(factory.isAlias("alias-name-1"));

        // 通过名称获取对应的所有别名
        System.out.println(List.of(factory.getAliases("name")));

        // 最后获取这个 bean 对象
        System.out.println(factory.getBean("name"));
    }
}
