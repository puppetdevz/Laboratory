package top.puppetdev.demo.demo04;

import java.lang.annotation.*;

/**
 * @author puppet
 * @since 2022-11-12 下午 10:32
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
@Documented
public @interface MyAutowired {
}
