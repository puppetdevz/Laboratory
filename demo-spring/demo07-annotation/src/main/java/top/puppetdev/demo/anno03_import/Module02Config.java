package top.puppetdev.demo.anno03_import;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 模块 2 配置类
 * @author puppet
 * @since 2022-10-07 下午 09:08
 */
@Configuration
public class Module02Config {
    @Bean
    public String module2() {
        return "I am the config of module 2";
    }
}
