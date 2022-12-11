package top.puppetdev.demo.demo03;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Service;

/**
 * @author puppet
 * @since 2022-11-17 下午 02:50
 */
@Service
public class UserRegisterService implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    public void registerUser(String username) {
        System.out.printf("用户【%s】注册成功", username);
        this.applicationEventPublisher.publishEvent(new UserRegisterEvent(this, username));
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }
}
