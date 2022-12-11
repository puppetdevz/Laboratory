package top.puppetdev.demo;

import org.junit.Test;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.*;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.demo01.Car;
import top.puppetdev.demo.demo01.CompositeObj;
import top.puppetdev.demo.demo01.User;
import top.puppetdev.demo.demo02.Service1;
import top.puppetdev.demo.demo02.Service2;
import top.puppetdev.demo.demo07.MyBean;
import top.puppetdev.demo.demo08.MyService;

import java.util.List;

/**
 * @author puppet
 * @since 2022-10-30 上午 02:35
 */
public class BeanLifeCycleTest {
    
    @Test
    public void test1() {
        // 指定 class
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class);
        // 获取 BeanDefinition
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);
    }
    
    @Test
    public void test2() {
        // 指定class
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Car.class);
        // 设置普通类型属性
        beanDefinitionBuilder.addPropertyValue("name", "奥迪");
        // 获取 BeanDefinition
        AbstractBeanDefinition carBeanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(carBeanDefinition);
        
        // 创建 Spring 容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 调用 registerBeanDefinition 向容器注册 bean
        factory.registerBeanDefinition("car", carBeanDefinition);
        Car carBean = factory.getBean("car", Car.class);
        System.out.println(carBean);
    }
    
    @Test
    public void test3() {
        // 创建 car 的 BeanDefinition
        AbstractBeanDefinition carBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(Car.class)
                .addPropertyValue("name", "宝马")
                .getBeanDefinition();
        // 创建 user 的 BeanDefinition
        AbstractBeanDefinition userBeanDefinition = BeanDefinitionBuilder.rootBeanDefinition(User.class)
                .addPropertyValue("name", "木偶")
                .addPropertyReference("car", "car")
                .getBeanDefinition();
        // 创建 Spring 容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 调用 registerBeanDefinition 向容器注册 bean
        factory.registerBeanDefinition("car", carBeanDefinition);
        factory.registerBeanDefinition("user", userBeanDefinition);
        System.out.println(factory.getBean("car"));
        System.out.println(factory.getBean("user"));
    }
    
    @Test
    public void test4() {
        // 创建 carBeanDefinition
        BeanDefinition carBeanDefinition1 = BeanDefinitionBuilder
                .genericBeanDefinition(Car.class)
                .addPropertyValue("name", "宝马")
                .getBeanDefinition();
        
        BeanDefinition carBeanDefinition2 = BeanDefinitionBuilder
                .genericBeanDefinition() //内部生成一个GenericBeanDefinition对象
                .setParentName("car1") //@1：设置父bean的名称为car1
                .getBeanDefinition();
        
        // 创建 Spring 容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        // 调用 registerBeanDefinition 向容器中注册bean
        // 注册 car1->carBeanDefinition1
        factory.registerBeanDefinition("car1", carBeanDefinition1);
        // 注册 car2->carBeanDefinition2
        factory.registerBeanDefinition("car2", carBeanDefinition2);
        // 从容器中获取car1
        System.out.println(String.format("car1 -> %s", factory.getBean("car1")));
        //从容器中获取car2
        System.out.println(String.format(" car2 -> %s ", factory.getBean("car2")));
    }
    
    @Test
    public void test5() {
        // 定义 car1
        BeanDefinition car1 = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "奥迪").
                getBeanDefinition();
        // 定义 car2
        BeanDefinition car2 = BeanDefinitionBuilder.
                genericBeanDefinition(Car.class).
                addPropertyValue("name", "保时捷").
                getBeanDefinition();
        
        // 定义 CompositeObj 这个 bean
        // 创建 stringList 这个属性对应的值
        ManagedList<String> stringList = new ManagedList<>();
        stringList.addAll(List.of("高并发系列", "mysql系列", "maven系列"));  // jdk 9 后才有 List.of()
        
        //创建 carList 这个属性对应的值, 内部引用其他两个 bean 的名称 [car1, car2]
        ManagedList<RuntimeBeanReference> carList = new ManagedList<>();
        carList.add(new RuntimeBeanReference("car1"));
        carList.add(new RuntimeBeanReference("car2"));
        
        //创建 stringSet 这个属性对应的值
        ManagedSet<String> stringSet = new ManagedSet<>();
        stringSet.addAll(List.of("高并发系列", "mysql系列", "maven系列"));
        
        //创建 carSet 这个属性对应的值, 内部引用其他两个 bean 的名称[car1, car2]
        ManagedList<RuntimeBeanReference> carSet = new ManagedList<>();
        carSet.add(new RuntimeBeanReference("car1"));
        carSet.add(new RuntimeBeanReference("car2"));
        
        //创建 stringMap 这个属性对应的值
        ManagedMap<String, String> stringMap = new ManagedMap<>();
        stringMap.put("系列1", "高并发系列");
        stringMap.put("系列2", "Maven系列");
        stringMap.put("系列3", "mysql系列");
        
        ManagedMap<String, RuntimeBeanReference> stringCarMap = new ManagedMap<>();
        stringCarMap.put("car1", new RuntimeBeanReference("car1"));
        stringCarMap.put("car2", new RuntimeBeanReference("car2"));
        
        
        // 使用原生的 api 来创建 BeanDefinition
        GenericBeanDefinition compositeObjBeanDefinition = new GenericBeanDefinition();
        compositeObjBeanDefinition.setBeanClassName(CompositeObj.class.getName());
        compositeObjBeanDefinition.getPropertyValues().add("name", "木偶").
                add("salary", 50000).
                add("car1", new RuntimeBeanReference("car1")).
                add("stringList", stringList).
                add("carList", carList).
                add("stringSet", stringSet).
                add("carSet", carSet).
                add("stringMap", stringMap).
                add("stringCarMap", stringCarMap);
        
        // 将上面 bean 注册到容器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        factory.registerBeanDefinition("car1", car1);
        factory.registerBeanDefinition("car2", car2);
        factory.registerBeanDefinition("compositeObj", compositeObjBeanDefinition);
        
        // 下面我们将容器中所有的 bean 输出
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s -> %s%n", beanName, factory.getBean(beanName));
        }
    }
    
    /**
     * xml 方式 bean 配置信息解析
     */
    @Test
    public void test6() {
        // 定义一个 Spring 容器，这个容器默认实现了 BeanDefinitionRegistry，所以本身就是一个 bean 注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        // 定义 xml 的 BeanDefinition 读取器，需要传递一个 BeanDefinitionRegistry（bean注册器）对象
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(factory);
        
        // 指定 beans1.xml 配置文件的位置
        String location = "classpath:beans1.xml";
        //通过 XmlBeanDefinitionReader 加载 beans1.xml 文件，然后将解析产生的 BeanDefinition 注册到容器中
        int countBean = xmlBeanDefinitionReader.loadBeanDefinitions(location);
        System.out.printf("共注册了 %s 个bean%n", countBean);
        
        // 打印出注册的 bean 的配置信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            // 通过名称从容器中获取对应的 BeanDefinition 信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            // 获取 BeanDefinition 具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            // 通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    }
    
    @Test
    public void test7() {
        // 定义一个 Spring 容器，这个容器默认实现了 BeanDefinitionRegistry，所以本身就是一个 bean 注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        // 定义一个 properties 的 BeanDefinition 读取器，需要传递一个 BeanDefinitionRegistry（bean注册器）对象
        PropertiesBeanDefinitionReader propertiesBeanDefinitionReader = new PropertiesBeanDefinitionReader(factory);
        
        // 指定 beans.properties 配置文件的位置
        String location = "classpath:beans.properties";
        //通过 PropertiesBeanDefinitionReader 加载 beans.properties 文件，然后将解析产生的 BeanDefinition 注册到容器中
        int countBean = propertiesBeanDefinitionReader.loadBeanDefinitions(location);
        System.out.printf("共注册了 %s 个bean%n", countBean);
        
        // 打印出注册的 bean 的配置信息
        for (String beanName : factory.getBeanDefinitionNames()) {
            // 通过名称从容器中获取对应的 BeanDefinition 信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            // 获取 BeanDefinition 具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            // 通过名称获取 bean 对象
            Object bean = factory.getBean(beanName);
            // 打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    }
    
    @Test
    public void test8() {
        // 定义一个 Spring 容器，这个容器默认实现了 BeanDefinitionRegistry，所以本身就是一个 bean 注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        // 定义注解方式的 BeanDefinition 读取器，需要传递一个 BeanDefinitionRegistry（bean注册器）对象
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(factory);
        
        // 通过 PropertiesBeanDefinitionReader 将解析产生的BeanDefinition注册到容器中
        annotatedBeanDefinitionReader.register(Service1.class, Service2.class);
        
        //打印出注册的bean的配置信息
        for (String beanName : new String[]{"service1", "service2"}) {
            //通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            //通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    }
    
    @Test
    public void test9() {
        // 定义一个 Spring 容器，这个容器默认实现了 BeanDefinitionRegistry，所以本身就是一个 bean 注册器
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        // 定义注解方式的 BeanDefinition 读取器，需要传递一个 BeanDefinitionRegistry（bean注册器）对象
        AnnotatedBeanDefinitionReader annotatedBeanDefinitionReader = new AnnotatedBeanDefinitionReader(factory);
        
        // 通过 PropertiesBeanDefinitionReader 将解析产生的BeanDefinition注册到容器中
        annotatedBeanDefinitionReader.register(Service1.class, Service2.class);
        
        // 加上这行代码后，service1 就能正常注入了
        factory.getBeansOfType(BeanPostProcessor.class).values().forEach(factory::addBeanPostProcessor);
        
        //打印出注册的bean的配置信息
        for (String beanName : new String[]{"service1", "service2"}) {
            //通过名称从容器中获取对应的BeanDefinition信息
            BeanDefinition beanDefinition = factory.getBeanDefinition(beanName);
            //获取BeanDefinition具体使用的是哪个类
            String beanDefinitionClassName = beanDefinition.getClass().getName();
            //通过名称获取bean对象
            Object bean = factory.getBean(beanName);
            //打印输出
            System.out.println(beanName + ":");
            System.out.println("    beanDefinitionClassName：" + beanDefinitionClassName);
            System.out.println("    beanDefinition：" + beanDefinition);
            System.out.println("    bean：" + bean);
        }
    }
    
    @Test
    public void testPostProcessBeforeInitialization() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MyBean.class);
        context.refresh();
    }
    
    /**
     * 初始化方法测试
     */
    @Test
    public void testInitMethod() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        BeanDefinition service = BeanDefinitionBuilder.genericBeanDefinition(MyService.class)
                .setInitMethodName("init") // 指定初始化方法
                .getBeanDefinition();
        
        factory.registerBeanDefinition("service", service);
        System.out.println(factory.getBean("service"));
    }
    
    /**
     * {@link BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)}
     * bean 初始化后置处理
     */
    @Test
    public void testPostProcessAfterInitialization() {
        DefaultListableBeanFactory factory = new DefaultListableBeanFactory();
        
        // 加入 bean 初始化后置处理器方法实现
        factory.addBeanPostProcessor(new BeanPostProcessor() {
            @Override
            public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
                System.out.println("postProcessAfterInitialization：" + beanName);
                return bean;
            }
        });
        
        // 下面注册 2 个 String 类型的 bean
        factory.registerBeanDefinition("name",
                BeanDefinitionBuilder
                        .genericBeanDefinition(String.class)
                        .addConstructorArgValue("木偶")
                        .getBeanDefinition());
        factory.registerBeanDefinition("personInformation",
                BeanDefinitionBuilder.genericBeanDefinition(String.class)
                        .addConstructorArgValue("兢兢业业学编程")
                        .getBeanDefinition());
        
        System.out.println("------- 输出 bean 信息 ---------");
        for (String beanName : factory.getBeanDefinitionNames()) {
            System.out.printf("%s -> %s%n", beanName, factory.getBean(beanName));
        }
    }
    
}
