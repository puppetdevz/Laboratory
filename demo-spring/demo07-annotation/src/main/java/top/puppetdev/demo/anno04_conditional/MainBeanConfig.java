package top.puppetdev.demo.anno04_conditional;

import org.springframework.context.annotation.Import;

/**
 * @author puppet
 * @since 2022-10-13 下午 12:10
 */
@Import({DevBeanConfig.class, TestBeanConfig.class, ProdBeanConfig.class})
public class MainBeanConfig {
}
