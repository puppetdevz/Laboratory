package top.puppetdev.demo.demo01;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.Soundbank;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author puppet
 * @since 2022-12-31 下午 12:58
 */
@Service
public class ArticleService {
    private Map<Long, String> articleMap = new HashMap<>();

    @Cacheable("demoCache")
    public List<String> list() {
        System.out.println("获取文章列表！");
        return List.of("hurry", "freeze", "tobacco", "trunk", "face");
    }

    @Cacheable(cacheNames = "demoCache", key = "#root.targetClass.name+'-'+#page+'-'+#pageSize")
    public String getPage(int page, int pageSize) {
        String message = String.format("page-%s-pageSize-%s", page, pageSize);
        System.out.println("从db中获取数据：" + message);
        return message;
    }

    @Cacheable(cacheNames = "demoCache", key = "'getById-'+#id", condition = "#cached")
    public String getById(long id, boolean cached) {
        System.out.println("获取数据！");
        return "Spring缓存：" + UUID.randomUUID();
    }

    @Cacheable(cacheNames = "demoCache", key = "'findById-'+#id", unless = "#result==null")
    public String findById(long id) {
        this.articleMap.put(1L, "Spring系列");
        System.out.println("获取文章：" + id);
        return articleMap.get(id);
    }

    @CachePut(value = "demoCache", key = "'getById-'+#id")
    public String add(long id, String content) {
        System.out.println("add new article: " + id);
        this.articleMap.put(id, content);
        return content;
    }

    @CacheEvict(cacheNames = "demoCache", key = "'findById-'+#id")
    public void delete(long id) {
        System.out.println("删除文章：" + id);
        this.articleMap.remove(id);
    }
}
