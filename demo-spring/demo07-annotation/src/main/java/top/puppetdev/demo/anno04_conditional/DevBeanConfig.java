package top.puppetdev.demo.anno04_conditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author puppet
 * @since 2022-10-13 下午 12:07
 */
@Configuration
@EnvConditional(EnvConditional.Env.DEV)
public class DevBeanConfig {
    @Bean
    public String name() {
        return "开发环境";
    }
}
