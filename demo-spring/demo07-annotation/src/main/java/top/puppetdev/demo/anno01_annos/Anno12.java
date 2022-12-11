package top.puppetdev.demo.anno01_annos;

import java.lang.annotation.*;

/**
 * @author puppet
 * @since 2022/9/29 21:35
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
@Repeatable(Anno12s.class)  // 注意此处
public @interface Anno12 {
    String value();
}
