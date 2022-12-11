package top.puppetdev.demo;

import lombok.Setter;
import lombok.ToString;

/**
 * @author puppet
 * @since 2022/9/12 17:54
 */
@ToString
@Setter
public class UserModel {
    
    private String name;
    private int age;
    
    public UserModel() {
        this.name = "我是通过UserModel的无参构造方法创建的!";
    }
    
    public UserModel(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
