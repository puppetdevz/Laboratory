package top.puppetdev.demo.demo02;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author puppet
 * @since 2023/1/2 20:24
 */
@Component
public class BookService {
    @Cacheable(value = "demoCache", key = "#root.targetClass.name+'-'+#root.methodName")
    public List<String> list() {
        System.out.println("模拟从DB中获取数据");
        return List.of("middle", "alone", "average", "aunt", "root");
    }
}
