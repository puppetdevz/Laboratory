package top.puppetdev.demo.demo01;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author puppet
 * @since 2022-12-31 下午 01:11
 */
@ComponentScan
@EnableCaching
@Configuration
public class MainConfig {
    @Bean  // 缓存管理器
    public CacheManager cacheManager() {
        // 创建缓存管理器 ConcurrentMapCacheManager（内部使用 ConcurrentMap 实现），构造器用来指定缓存的名称，可以指定多个
        return new ConcurrentMapCacheManager("demoCache");
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        ArticleService articleService = context.getBean(ArticleService.class);
        // 调用两次 top.puppetdev.demo.demo01.ArticleService.list
        System.out.println(articleService.list());
        System.out.println(articleService.list());
    }
}
