package top.puppetdev.demo.demo03;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

/**
 * @author puppet
 * @since 2022-11-17 下午 02:44
 */
@Getter
public class UserRegisterEvent extends ApplicationEvent {
    private String username;

    public UserRegisterEvent(Object source, String username) {
        super(source);
        this.username = username;
    }
}
