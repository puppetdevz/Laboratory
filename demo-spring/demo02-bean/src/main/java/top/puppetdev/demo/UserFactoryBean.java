package top.puppetdev.demo;

import org.springframework.beans.factory.FactoryBean;

/**
 * @author puppet
 * @since 2022/9/13 19:09
 */
public class UserFactoryBean implements FactoryBean {
    // 通过 count，可以看出 isSingleton 不同返回值时，从容器获取的 bean 是否是同一个
    private int count;
    
    @Override
    public Object getObject() throws Exception {
        UserModel user = new UserModel();
        user.setName("我是通过FactoryBean创建的第 " + count++ + " 对象");
        return user;
    }
    
    @Override
    public Class<?> getObjectType() {
        return UserModel.class;
    }
    
    @Override
    public boolean isSingleton() {
        // 返回 true，表示创建的对象是单例的，那么我们每次从容器中获取这个对象的时候都是同一个
        return false;
    }
}
