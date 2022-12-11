package top.puppetdev.demo.anno03_import;

import org.springframework.context.annotation.Import;

/**
 * @author puppet
 * @since 2022-10-07 下午 08:11
 */
@Import({Service1.class, Service2.class})
public class MainBeansConfig01 {
}
