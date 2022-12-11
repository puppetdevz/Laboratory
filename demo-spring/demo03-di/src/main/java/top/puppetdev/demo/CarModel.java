package top.puppetdev.demo;

import lombok.Setter;
import lombok.ToString;

/**
 * @author puppet
 * @since 2022/9/12 17:54
 */
@ToString
@Setter
public class CarModel {
    
    private String name;
    private String desc;
    
    public CarModel() {
    }
    
    public CarModel(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
