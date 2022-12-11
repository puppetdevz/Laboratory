package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.lang.Nullable;
import top.puppetdev.demo.demo01.Car;
import top.puppetdev.demo.demo05.UserModel;

/**
 * bean 初始化前阶段，会调用：{@link org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor#postProcessBeforeInitialization(Object, String)}
 * @author puppet
 * @since 2022-11-12 下午 08:34
 */
public class InstantiationAwareBeanPostProcessorTest {
    @Test
    public void test1() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 添加一个 BeanProcessor：InstantiationAwareBeanPostProcessor
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
                System.out.println("调用postProcessBeforeInstantiation()");
                // 发现类型为 Car 时，硬编码创建一个 Car 对象返回
                if (beanClass == Car.class) {
                    Car car = new Car();
                    car.setName("宝马");
                    return car;
                }
                return null;
            }
        });

        // 定义个 car bean，车名：奥迪
        AbstractBeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(Car.class)
                .addPropertyValue("name", "奥迪")
                .getBeanDefinition();
        factory.registerBeanDefinition("car", beanDefinition);
        // 从容器中获取 car 这个 bean 的实例
        System.out.println(factory.getBean("car"));
    }

    /**
     * {@link InstantiationAwareBeanPostProcessor#postProcessAfterInstantiation(java.lang.Object, java.lang.String)}
     * 返回 false，可以阻止 bean 属性的赋值
     */
    @Test
    public void test2() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 添加一个 InstantiationAwareBeanPostProcessor，如果 beanName 为 user1 时，则不对其属性赋值
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Override
            public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
                return !"user1".equals(beanName);
            }
        });

        // 注册 user1 到容器
        factory.registerBeanDefinition("user1", BeanDefinitionBuilder.genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "木偶")
                .addPropertyValue("age", 18)
                .getBeanDefinition()
        );
        // 注册 user2 到容器
        factory.registerBeanDefinition("user2", BeanDefinitionBuilder.genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "哈哈")
                .addPropertyValue("age", 20)
                .getBeanDefinition()
        );

        for (String beanDefinitionName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s -> %s\n", beanDefinitionName, factory.getBean(beanDefinitionName));
        }
    }

    @Test
    public void test3() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();

        // 通过实现重写 postProcessProperties 方法，在内部对 user1 进行属性赋值
        factory.addBeanPostProcessor(new InstantiationAwareBeanPostProcessor() {
            @Nullable
            @Override
            public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
                // 如果当前 bean 为 user1
                if ("user1".equals(beanName)) {
                    // 如果 pvs 是 MutablePropertyValues 的一个实例，则将其强转为 MutablePropertyValues 类型，并赋给 mpvs
                    if (pvs instanceof MutablePropertyValues mpvs) {
                        // 将姓名设置为：木偶
                        mpvs.add("name", "木偶");
                        // 将年龄属性的值修改为 18
                        mpvs.add("age", 18);
                    }
                }
                return null;
            }
        });

        // 注意：user1 没有给属性设置值
        factory.registerBeanDefinition("user1", BeanDefinitionBuilder.genericBeanDefinition(UserModel.class)
                .getBeanDefinition());

        // user2 给属性设置值了
        factory.registerBeanDefinition("user2", BeanDefinitionBuilder.genericBeanDefinition(UserModel.class)
                .addPropertyValue("name", "赵丽颖")
                .addPropertyValue("age", 30)
                .getBeanDefinition());

        // 遍历输出所有 bean 对象
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s -> %s%n", beanName, factory.getBean(beanName));
        }
    }
}
