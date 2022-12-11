package top.puppetdev.demo;

/**
 * @author puppet
 * @since 2022/9/13 18:49
 */
public class UserFactory {
    public UserModel createNoArgsUser() {
        System.out.println("----------------------1");
        UserModel user = new UserModel();
        user.setName("bean实例方法创建的对象!");
        return user;
    }
    
    public UserModel createFullArgsUser(String name, int age) {
        System.out.println("----------------------2");
        return new UserModel(name, age);
    }
}
