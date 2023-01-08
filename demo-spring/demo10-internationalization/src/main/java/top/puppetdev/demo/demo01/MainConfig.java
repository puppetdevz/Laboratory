package top.puppetdev.demo.demo01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * @author puppet
 * @since 2022-11-16 下午 07:25
 */
@Configuration
public class MainConfig {
    @Bean
    public ResourceBundleMessageSource messageSource() {
        ResourceBundleMessageSource result = new ResourceBundleMessageSource();
        // 可以指定国际化配置文件的位置
        // 格式：路径/文件名称，注意不包含【语言_国家.properties】含这部分
        result.setBasename("message");
        return result;
    }
}
