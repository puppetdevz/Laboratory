package top.puppetdev.demo;

/**
 * @author puppet
 * @since 2022/9/13 13:14
 */
public class UserStaticFactory {
    public static UserModel createNoArgsUser() {
        System.out.println(UserStaticFactory.class + ".createNoArgsUser()");
        UserModel userModel = new UserModel();
        userModel.setName("我是无参构造器创建的");
        return userModel;
    }
    
    public static UserModel createFullArgsUser(String name, int age) {
        System.out.println(UserStaticFactory.class + ".createFullArgsUser()");
        return new UserModel(name, age);
    }
}
