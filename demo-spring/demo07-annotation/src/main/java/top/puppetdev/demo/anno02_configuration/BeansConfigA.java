package top.puppetdev.demo.anno02_configuration;

import org.springframework.context.annotation.Bean;

/**
 * @author puppet
 * @since 2022/9/30 01:49
 */
public class BeansConfigA {

    // bean 名称为方法默认值：userA
    @Bean
    public User userA() {
        return new User();
    }

    // bean 名称通过 value 指定了：userBBean
    @Bean("userBBean")
    public User userB() {
        return new User();
    }

    // bean 名称为：userCBean，2 个别名：[userCBeanAlias1,userCBeanAlias2]
    @Bean({"userCBean", "userCBeanAlias1", "userCBeanAlias2"})
    public User userC() {
        return new User();
    }
}
