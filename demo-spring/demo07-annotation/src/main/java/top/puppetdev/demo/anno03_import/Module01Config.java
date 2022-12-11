package top.puppetdev.demo.anno03_import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 模块 1 配置类
 * @author puppet
 * @since 2022-10-07 下午 09:08
 */
@Configuration
public class Module01Config {
    @Bean
    public String module1() {
        return "I am the config of module 1";
    }

    @Bean
    public String name() {
        return "puppet";
    }

    @Bean
    public String address() {
        return "hello";
    }
}
