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
    private String desc;
    
    public UserModel() {
    }
    
    public UserModel(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    public UserModel(String name, int age, String desc) {
        this.name = name;
        this.age = age;
        this.desc = desc;
    }
}
