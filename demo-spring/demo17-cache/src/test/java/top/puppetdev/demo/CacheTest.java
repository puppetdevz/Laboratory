package top.puppetdev.demo;

import org.junit.Before;
import org.junit.Test;
import org.springframework.cache.Cache;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import top.puppetdev.demo.demo01.ArticleService;
import top.puppetdev.demo.demo01.MainConfig;

import javax.naming.PartialResultException;
import java.security.cert.TrustAnchor;
import java.util.TreeMap;

/**
 * @author puppet
 * @since 2023/1/1 21:13
 */
public class CacheTest {

    private AnnotationConfigApplicationContext context;

    @Before
    public void setUp() {
        context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
    }

    @Test
    public void testCustomStrategyToGenerateKey() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.register(MainConfig.class);
        context.refresh();
        ArticleService articleService = context.getBean(ArticleService.class);

        // page=1, pageSize=10 调用两次
        System.out.println(articleService.getPage(1, 10));
        System.out.println(articleService.getPage(1, 10));
        // page=2, pageSize=10 调用两次
        System.out.println(articleService.getPage(2, 10));
        System.out.println(articleService.getPage(2, 10));

        System.out.println("下面打印出 demoCache 缓存中的 key 列表");
        ConcurrentMapCacheManager cacheManager = context.getBean(ConcurrentMapCacheManager.class);
        ConcurrentMapCache demoCache = (ConcurrentMapCache) cacheManager.getCache("demoCache");
        demoCache.getNativeCache().keySet().stream().forEach(System.out::println);
    }

    @Test
    public void testCondition() {
        ArticleService articleService = context.getBean(ArticleService.class);
        System.out.println(articleService.getById(1L, true));
        System.out.println(articleService.getById(1L, true));
        System.out.println(articleService.getById(1L, false));
        System.out.println(articleService.getById(1L, true));
    }

    @Test
    public void testCachePut() {
        ArticleService articleService = context.getBean(ArticleService.class);

        // 新增 3 篇文章，由于 add 方法上面有 @CachePut 注解，所以新增之后会自动丢到缓存中
        articleService.add(1L, "Java高并发系列");
        articleService.add(2L, "Maven高手系列");
        articleService.add(3L, "MySQL高手系列");

        // 然后调用 findById 获取，看看是否会走缓存
        System.out.println("调用 findById 方法，会尝试从缓存中获取");
        System.out.println(articleService.getById(1L, true));
        System.out.println(articleService.getById(2L, true));
        System.out.println(articleService.getById(3L, true));

        System.out.println("下面打印出 demoCache 缓存中的 key 列表");
        ConcurrentMapCacheManager cacheManager = context.getBean(ConcurrentMapCacheManager.class);
        ConcurrentMapCache demoCache = (ConcurrentMapCache) cacheManager.getCache("demoCache");
        demoCache.getNativeCache().keySet().stream().forEach(System.out::println);
    }

    @Test
    public void testCacheEvict() {
        ArticleService articleService = context.getBean(ArticleService.class);

        // 第 1 次调用 findById，缓存中没有，则调用方法，将结果丢到缓存中
        System.out.println(articleService.findById(1L));
        // 第 2 次调用 findById，缓存中存在，直接从缓存中获取
        System.out.println(articleService.findById(1L));

        // 执行删除操作，delete 方法上面有 @CacheEvict 方法，会清除缓存
        articleService.delete(1L);

        // 再次调用 findById 方法，发现缓存中没有了，则会调用目标方法
        System.out.println(articleService.findById(1L));
    }
}
