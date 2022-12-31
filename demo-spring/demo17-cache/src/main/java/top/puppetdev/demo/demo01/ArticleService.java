package top.puppetdev.demo.demo01;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author puppet
 * @since 2022-12-31 下午 12:58
 */
@Service
public class ArticleService {

    @Cacheable("demoCache")
    public List<String> list() {
        System.out.println("获取文章列表！");
        return List.of("hurry", "freeze", "tobacco", "trunk", "face");
    }
}
