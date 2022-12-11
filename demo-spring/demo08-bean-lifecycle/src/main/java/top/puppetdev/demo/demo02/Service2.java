package top.puppetdev.demo.demo02;

import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author puppet
 * @since 2022-11-10 下午 07:35
 */
@ToString
public class Service2 {
    @Autowired
    private Service1 service1;
}
