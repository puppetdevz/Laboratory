package top.puppetdev.demo;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 自定义本地线程级别的 bean 作用域，不同的线程中对应的bean实例是不同的，
 * 同一个线程中同名的 bean 是同一个实例
 * @author puppet
 * @since 2022/9/18 14:40
 */
public class CustomThreadScope implements Scope {
    // 定义作用域的名称为一个常量 thread，可以在定义 bean 的时候给 scope 使用
    public static final String THREAD_SCOPE = "thread";
    
    private ThreadLocal<Map<String, Object>> beanMap = new ThreadLocal<>() {
        @Override
        protected Map<String, Object> initialValue() {
            return new HashMap<>();
        }
    };
    
    /**
     * 返回当前作用域中的 name 对应的 bean 对象
     * name：需要检索的 bean 的名称
     * objectFactory：如果 name 对应的 bean 在当前作用域中没有找到，那么可以调用这个ObjectFactory来创建这个对象
     **/
    @Override
    public Object get(String name, ObjectFactory<?> objectFactory) {
        Object bean = beanMap.get().get(name);
        if (Objects.isNull(bean)) {
            bean = objectFactory.getObject();
            beanMap.get().put(name, bean);
        }
        return bean;
    }
    
    /**
     * 将 name 对应的 bean 从当前作用域中移除
     **/
    @Nullable
    @Override
    public Object remove(String name) {
        return this.beanMap.get().remove(name);
    }
    
    /**
     * 用于注册销毁回调，如果想要销毁相应的对象，
     * 则由 Spring 容器注册相应的销毁回调，并由自定义作用域自己决定是否要销毁相应的对象
     */
    @Override
    public void registerDestructionCallback(String name, Runnable callback) {
        // bean 作用域范围结束时调用的方法，用于 bean 清理
        System.out.println(name);
    }
    
    /**
     * 用于解析相应的上下文数据，比如 request 作用域将返回 request 中的属性。
     */
    @Nullable
    @Override
    public Object resolveContextualObject(String key) {
        return null;
    }
    
    /**
     * 作用域的会话标识，比如 session 作用域将是 sessionId
     */
    @Nullable
    @Override
    public String getConversationId() {
        return Thread.currentThread().getName();
    }
}
