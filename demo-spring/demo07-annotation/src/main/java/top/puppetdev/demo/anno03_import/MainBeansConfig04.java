package top.puppetdev.demo.anno03_import;

import org.springframework.context.annotation.Import;

/**
 * 通过 Import 导入 ImportSelector 接口实现类
 * @author puppet
 * @since 2022-10-07 下午 08:11
 */
@Import(MyImportSelector.class)
public class MainBeansConfig04 {
}
