package top.puppetdev.demo.demo02;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.cache.RedissonCache;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * @author puppet
 * @since 2023/1/2 17:51
 */
@ComponentScan
@EnableCaching  // 开启 Spring 缓存功能
public class MainConfig {
    // 自定义 Spring 中的 CacheManager，底层使用 Redis 来作为缓存存储介质
    @Bean
    public CacheManager cacheManager(@Autowired RedissonClient redissonClient) throws IOException {
        RedissonSpringCacheManager cacheManager = new RedissonSpringCacheManager(redissonClient);
        cacheManager.setCacheNames(List.of("demoCache"));
        return cacheManager;
    }

    // 通过 redis.yml 配置文件创建一个 RedissonClient 用于和 Redis 进行交互
    @Bean
    public RedissonClient redissonClient() throws IOException {
        InputStream inputStream = this.getClass().getResourceAsStream("redis.yml");
        Config config = Config.fromYAML(inputStream);
        return Redisson.create(config);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
        BookService bookService = context.getBean(BookService.class);
        System.out.println(bookService.list());
        System.out.println(bookService.list());

        System.out.println("下面打印出 demoCache 缓存中的 key 列表");
        RedissonSpringCacheManager cacheManager = context.getBean(RedissonSpringCacheManager.class);
        RedissonCache demoCache = (RedissonCache) cacheManager.getCache("demoCache");
        demoCache.getNativeCache().keySet().stream().forEach(System.out::println);
    }
}
