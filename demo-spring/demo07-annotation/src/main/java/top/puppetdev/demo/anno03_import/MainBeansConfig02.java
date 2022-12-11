package top.puppetdev.demo.anno03_import;

import org.springframework.context.annotation.Import;

/**
 * 通过 Import 汇总多个 @Configuration 修饰的配置类
 * @author puppet
 * @since 2022-10-07 下午 08:11
 */
@Import({Module01Config.class, Module02Config.class})
public class MainBeansConfig02 {
}
