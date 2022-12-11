package top.puppetdev.demo;

import lombok.Setter;
import lombok.ToString;

/**
 * @author puppet
 * @since 2022/9/21 16:18
 */
@ToString
@Setter
public class PersonModel {
    private CarModel carModel;
    private UserModel userModel;
    
    public PersonModel(CarModel carModel, UserModel userModel) {
        this.carModel = carModel;
        this.userModel = userModel;
    }
    
    public PersonModel() {
    }
}
