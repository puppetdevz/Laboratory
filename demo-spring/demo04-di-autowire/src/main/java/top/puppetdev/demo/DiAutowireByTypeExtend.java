package top.puppetdev.demo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author puppet
 * @since 2022/9/23 16:02
 */
@ToString
@Setter
@Getter
public class DiAutowireByTypeExtend {
    
    // 定义了一个接口
    public interface IService {
    }
    
    @ToString
    @Setter
    public static class BaseService {
        private String desc;
    }
    
    // Service1 继承了 BaseService 并实现了 IService 接口
    public static class Service1 extends BaseService implements IService {
    
    }
    
    // Service2 继承了 BaseService 并实现了 IService 接口
    public static class Service2 extends BaseService implements IService {
    }
    
    
    // 参数类型是 List<IService>，这个集合集合中元素的类型是 IService，
    // Spring 会找到容器中所有满足 IService.isAssignableFrom(bean的类型)的 bean 对象，
    // 然后将其通过 serviceList 的 set 方法注入
    // 其他的 List 和 Map 也同理
    private List<IService> serviceList;
    private List<BaseService> baseServiceList;
    private Map<String, IService> serviceMap;
    private Map<String, BaseService> baseServiceMap;
}
